package com.skyline.skysmart.device.data.bo.impls;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.interfaces.ISceneBO;
import com.skyline.skysmart.device.data.dao.SceneDAO;

import java.util.HashMap;

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
    private HashMap<String, String> instructionsMap;

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
        this.instructionsMap = JSONObject.parseObject(instructionsJson, new TypeReference<HashMap<String, String>>(){});
    }

    /**
     * set instructions of SceneDAO in Json type
     *
     * @param instructionsJson String, Json type
     */
    @Override
    public void setInstructions(String instructionsJson) {
        assertSceneNotEmpty();
        this.sceneDAO.setInstructionsJson(instructionsJson);
    }

    /**
     * set instructions of SceneDAO in HashMap type
     *
     * @param instructionsMap HashMap
     */
    @Override
    public void setInstructions(HashMap<String, String> instructionsMap) {
        assertSceneNotEmpty();
        String instructionsJson = new org.json.JSONObject(instructionsMap).toString();
        this.sceneDAO.setInstructionsJson(instructionsJson);
    }

    /**
     * get instructions map of SceneBO
     *
     * @return HashMap
     */
    @Override
    public HashMap<String, String> getInstructions() {
        assertInstructionsMapNotEmpty();
        return this.instructionsMap;
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
    public void assertInstructionsMapNotEmpty() {
        if (this.instructionsMap == null || this.instructionsMap.isEmpty()) {
            Asserts.fail(ResultCode.NULL);
        }
    }
}
