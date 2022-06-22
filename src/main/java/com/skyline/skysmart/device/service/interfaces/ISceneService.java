package com.skyline.skysmart.device.service.interfaces;

import com.skyline.skysmart.device.data.dao.SceneDAO;

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
     * toggle whether scene is active
     *
     * @param uuid String
     * @param active Boolean
     */
    void toggleActive(String uuid, Boolean active);

}
