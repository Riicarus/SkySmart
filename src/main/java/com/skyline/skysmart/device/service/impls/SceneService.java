package com.skyline.skysmart.device.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.dao.SceneDAO;
import com.skyline.skysmart.device.mapper.SceneMapper;
import com.skyline.skysmart.device.service.interfaces.ISceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * [FEATURE INFO]<br/>
 * scene service
 *
 * @author Skyline
 * @create 2022/6/22 14:47
 * @since 1.0.0
 */
@Service
public class SceneService implements ISceneService {

    private final HashSet<String> cachedScene = new HashSet<>();

    private SceneMapper sceneMapper;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setSceneMapper(SceneMapper sceneMapper) {
        this.sceneMapper = sceneMapper;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * get scene by uuid
     *
     * @param uuid String, uuid
     * @return SceneDAO
     */
    @Override
    public SceneDAO getSceneById(String uuid) {
        return sceneMapper.selectById(uuid);
    }

    /**
     * cache active scene from database into redis when project load
     */
    @Override
    public void cacheActiveScene() {
        QueryWrapper<SceneDAO> sceneWrapper = new QueryWrapper<>();
        sceneWrapper.eq("active", true);
        List<SceneDAO> sceneDAOS = sceneMapper.selectList(sceneWrapper);

        if (sceneDAOS.isEmpty()) {
            System.out.println("NO ACTIVE SCENE.");
        }

        for (SceneDAO sceneDAO : sceneDAOS) {
            String key = RedisKeyPrefix.ACTIVE_SCENE.getKeyPrefix() + sceneDAO.getUuid();
            redisTemplate.opsForValue().set(key, sceneDAO);
            cachedScene.add(sceneDAO.getUuid());
        }
    }

    /**
     * toggle whether scene is active<br/>
     *
     * update db ==> update redis
     *
     * @param uuid   String
     * @param active Boolean
     */
    @Override
    public void toggleActive(String uuid, Boolean active) {
        // get scene from db
        SceneDAO sceneDAO = getSceneById(uuid);
        if (sceneDAO == null) {
            Asserts.fail(ResultCode.NO_ELEMENT);
        }

        sceneDAO.setActive(!sceneDAO.getActive());

        // update database
        int res = sceneMapper.updateById(sceneDAO);
        if (res != 1) {
            Asserts.fail(ResultCode.FAILED);
        }

        // is cached? to cache/remove in redis
        String key = RedisKeyPrefix.ACTIVE_SCENE.getKeyPrefix() + uuid;
        if (cachedScene.contains(uuid)) {
            // remove
            redisTemplate.delete(key);
            cachedScene.remove(uuid);
        } else {
            // cache
            redisTemplate.opsForValue().set(key, sceneDAO);
            cachedScene.add(uuid);
        }
    }
}
