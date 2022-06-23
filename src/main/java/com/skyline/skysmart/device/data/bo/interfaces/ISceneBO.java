package com.skyline.skysmart.device.data.bo.interfaces;

import com.skyline.skysmart.device.data.dao.SceneDAO;

import java.util.Queue;
import java.util.Set;

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
     * @param instructionsJson String, Json type, raw type: Queue of String
     */
    void setInstructionsJson(String instructionsJson);

    /**
     * set instructions of SceneDAO in Queue type
     *
     * @param unitQueue Queue, instruction queue
     */
    void setInstructionsJson(Queue<String> unitQueue);

    /**
     * get instruction unit queue of SceneBO
     *
     * @return Queue
     */
    Queue<String> getInstructionQueue();

    /**
     * bind device set of SceneBO
     *
     * @param instructionsJson String, Json type, raw type: Queue of String
     */
    void mapDeviceSet(String instructionsJson);

    /**
     * get device set
     *
     * @return Set of uuid
     */
    Set<String> getDeviceSet();

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
     * get uuid
     *
     * @return String
     */
    String getUuid();

    /**
     * set uuid
     *
     * @param uuid String
     */
    void setUuid(String uuid);

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
    void assertInstructionQueueNotEmpty();

    /**
     * assert device set not empty
     */
    void assertDeviceSetNotEmpty();
}
