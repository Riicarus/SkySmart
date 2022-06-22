package com.skyline.skysmart.device.data.bo.interfaces;

import com.skyline.skysmart.device.data.dao.SceneDAO;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * scene bo interface
 *
 * @author Skyline
 * @create 2022/6/22 14:25
 * @since 1.0.0
 */
public interface ISceneBO {

    /**
     * set scene dao
     *
     * @param sceneDAO SceneDAO
     */
    void mapperSceneDAO(SceneDAO sceneDAO);

    /**
     * get scene dao
     *
     * @return SceneDAO
     */
    SceneDAO getSceneDAO();

    /**
     * bind instructions of SceneBO
     *
     * @param instructionsJson String, Json type
     */
    void mapInstructions(String instructionsJson);

    /**
     * set instructions of SceneDAO in Json type
     *
     * @param instructionsJson String, Json type
     */
    void setInstructions(String instructionsJson);

    /**
     * set instructions of SceneDAO in HashMap type
     *
     * @param instructionsMap HashMap
     */
    void setInstructions(HashMap<String, String> instructionsMap);

    /**
     * get instructions map of SceneBO
     *
     * @return HashMap
     */
    HashMap<String, String> getInstructions();

    /**
     * get scene name
     *
     * @return String
     */
    String getName();

    /**
     * set scene name
     *
     * @param name String
     */
    void setName(String name);

    /**
     * get is scene active
     *
     * @return boolean
     */
    boolean isActive();

    /**
     * set is scene active
     *
     * @param active boolean
     */
    void setActive(boolean active);

    /**
     * assert scene not empty
     */
    void assertSceneNotEmpty();

    /**
     * assert instructions map not empty
     */
    void assertInstructionsMapNotEmpty();
}
