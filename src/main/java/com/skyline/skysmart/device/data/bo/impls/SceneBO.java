package com.skyline.skysmart.device.data.bo.impls;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.interfaces.ISceneBO;
import com.skyline.skysmart.device.data.dao.SceneDAO;

import java.util.Queue;

/**
 * [FEATURE INFO]<br/>
 * scene bo
 *
 * @author Skyline
 * @create 2022/6/22 14:34
 * @since 1.0.0
 */
public class SceneBO implements ISceneBO {

    private SceneDAO sceneDAO;
    // instruction queue
    private Queue<String> instructionQueue;

    /**
     * set scene dao
     *
     * @param sceneDAO SceneDAO
     */
    @Override
    public void mapperSceneDAO(SceneDAO sceneDAO) {
        this.sceneDAO = sceneDAO;
    }

    /**
     * get scene dao
     *
     * @return SceneDAO
     */
    @Override
    public SceneDAO getSceneDAO() {
        assertSceneNotEmpty();
        return this.sceneDAO;
    }

    /**
     * bind instructions of SceneBO
     *
     * @param instructionsJson String, Json type
     */
    @Override
    public void mapInstructions(String instructionsJson) {
        this.instructionQueue = JSONObject.parseObject(instructionsJson, new TypeReference<Queue<String>>(){});
    }

    /**
     * set instructions of SceneDAO in Json type
     *
     * @param instructionsJson String, Json type
     */
    @Override
    public void setInstructionsJson(String instructionsJson) {
        assertSceneNotEmpty();
        this.sceneDAO.setInstructionsJson(instructionsJson);
    }

    /**
     * set instructions of SceneDAO in LinkedHashMap type
     *
     * @param instructionQueue Queue, instruction queue
     */
    @Override
    public void setInstructionsJson(Queue<String> instructionQueue) {
        assertSceneNotEmpty();
        String instructionsJson = JSONObject.toJSONString(instructionQueue);
        this.sceneDAO.setInstructionsJson(instructionsJson);
    }

    /**
     * get instruction queue of SceneBO
     *
     * @return Queue
     */
    @Override
    public Queue<String> getInstructionQueue() {
        assertInstructionQueueNotEmpty();
        return this.instructionQueue;
    }

    /**
     * get scene name
     *
     * @return String
     */
    @Override
    public String getName() {
        assertSceneNotEmpty();
        return this.sceneDAO.getName();
    }

    /**
     * set scene name
     *
     * @param name String
     */
    @Override
    public void setName(String name) {
        assertSceneNotEmpty();
        this.sceneDAO.setName(name);
    }

    /**
     * get is scene active
     *
     * @return boolean
     */
    @Override
    public boolean isActive() {
        assertSceneNotEmpty();
        return this.sceneDAO.getActive();
    }

    /**
     * set is scene active
     *
     * @param active boolean
     */
    @Override
    public void setActive(boolean active) {
        assertSceneNotEmpty();
        this.sceneDAO.setActive(active);
    }

    /**
     * assert scene not empty
     */
    @Override
    public void assertSceneNotEmpty() {
        if (this.sceneDAO == null) {
            Asserts.fail(ResultCode.NULL);
        }
    }

    /**
     * assert instructions map not empty
     */
    @Override
    public void assertInstructionQueueNotEmpty() {
        if (this.instructionQueue == null || this.instructionQueue.isEmpty()) {
            Asserts.fail(ResultCode.NULL);
        }
    }
}
