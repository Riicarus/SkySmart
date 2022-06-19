package com.skyline.skysmart.device.data.bo.impls;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.interfaces.IPresetBO;
import com.skyline.skysmart.device.data.dao.PresetDAO;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * PresetBO
 *
 * @author Skyline
 * @create 2022/6/17 12:05
 * @since 1.0.0
 */
public class PresetBO implements IPresetBO {

    private PresetDAO presetDAO;
    private HashMap<String, String> properties;

    /**
     * set preset dao
     *
     * @param presetDAO PresetDAO
     */
    @Override
    public void mapPresetDAO(PresetDAO presetDAO) {
        this.presetDAO = presetDAO;
    }

    /**
     * get preset dao
     *
     * @return PresetDAO
     */
    @Override
    public PresetDAO getPresetDAO() {
        assertPresetDAONotEmpty();
        return this.presetDAO;
    }

    /**
     * bind properties of preset
     *
     * @param properties String
     */
    @Override
    public void mapProperties(String properties) {
        this.properties = JSONObject.parseObject(properties, new TypeReference<HashMap<String, String>>(){});
    }

    /**
     * set PresetDAO's properties String
     *
     * @param properties String
     */
    @Override
    public void setProperties(String properties) {
        assertPresetDAONotEmpty();
        this.presetDAO.setProperties(properties);
    }

    /**
     * set PresetDAO's properties String
     *
     * @param propertiesMap HashMap
     */
    @Override
    public void setProperties(HashMap<String, String> propertiesMap) {
        assertPresetDAONotEmpty();
        String properties = new org.json.JSONObject(propertiesMap).toString();
        this.presetDAO.setProperties(properties);
    }

    /**
     * get properties of preset
     *
     * @return HashMap
     */
    @Override
    public HashMap<String, String> getProperties() {
        assertPropertiesNotEmpty();
        return this.properties;
    }

    /**
     * set preset name
     *
     * @param name String
     */
    @Override
    public void setPresetName(String name) {
        assertPresetDAONotEmpty();
        this.presetDAO.setName(name);
    }

    /**
     * get preset name
     *
     * @return String
     */
    @Override
    public String getPresetName() {
        assertPresetDAONotEmpty();
        return presetDAO.getName();
    }

    /**
     * assert preset not empty
     */
    @Override
    public void assertPresetDAONotEmpty() {
        if (this.presetDAO == null) {
            Asserts.fail(ResultCode.NULL);
        }
    }

    /**
     * assert properties not empty
     */
    @Override
    public void assertPropertiesNotEmpty() {
        if (properties == null || properties.isEmpty()) {
            Asserts.fail(ResultCode.NULL);
        }
    }
}
