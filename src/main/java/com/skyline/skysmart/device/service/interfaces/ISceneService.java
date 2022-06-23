package com.skyline.skysmart.device.service.interfaces;

import com.skyline.skysmart.device.data.bo.interfaces.ISceneBO;
import com.skyline.skysmart.device.data.dao.SceneDAO;
import com.skyline.skysmart.device.data.dto.SceneAddParam;

import java.util.List;

/**
 * [FEATURE INFO]<br/>
 * scene service interface
 *
 * @author Skyline
 * @create 2022/6/22 14:46
 * @since 1.0.0
 */
public interface ISceneService {

    /**
     * get scene by uuid
     *
     * @param uuid String, uuid
     * @return SceneDAO
     */
    SceneDAO getSceneById(String uuid);

    /**
     * cache active scene into redis when project load
     */
    void cacheActiveScene();

    /**
     * get all scene cache in redis
     *
     * @return List
     */
    List<ISceneBO> getCacheScene();

    /**
     * toggle whether scene is active
     *
     * @param uuid String
     */
    void toggleActive(String uuid);

    /**
     * add scene to db
     *
     * @param sceneAddParam SceneAddParam
     */
    void addScene(SceneAddParam sceneAddParam);

    /**
     * cache sceneDAO into redis
     *
     * @param sceneDAO SceneDAO
     */
    void cacheScene(SceneDAO sceneDAO);

    /**
     * remove sceneDAO from redis
     *
     * @param sceneDAO SceneDAO
     */
    void removeScene(SceneDAO sceneDAO);

    /**
     * send activate device request of this scene
     *
     * @param sceneBO ISceneBO
     */
    void sendActivateSceneRequest(ISceneBO sceneBO);

    /**
     * send deactivate device request of this scene
     *
     * @param sceneBO ISceneBO
     */
    void sendDeactivateSceneRequest(ISceneBO sceneBO);

}
