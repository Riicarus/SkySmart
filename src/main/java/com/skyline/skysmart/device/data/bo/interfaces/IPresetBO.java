package com.skyline.skysmart.device.data.bo.interfaces;

import com.skyline.skysmart.device.data.dao.PresetDAO;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * preset bo interface
 *
 * @author Skyline
 * @create 2022/6/17 12:01
 * @since 1.0.0
 */
public interface IPresetBO {

    /**
     * set preset dao
     *
     * @param presetDAO PresetDAO
     */
    void mapPresetDAO(PresetDAO presetDAO);

    /**
     * get preset dao
     *
     * @return PresetDAO
     */
    PresetDAO getPresetDAO();

    /**
     * bind properties of preset
     *
     * @param properties String
     */
    void mapProperties(String properties);

    /**
     * set PresetDAO's properties String
     *
     * @param properties String
     */
    void setProperties(String properties);

    /**
     * set PresetDAO's properties String
     *
     * @param propertiesMap HashMap
     */
    void setProperties(HashMap<String, String> propertiesMap);

    /**
     * get properties of preset
     *
     * @return HashMap
     */
    HashMap<String, String> getProperties();

    /**
     * set preset name
     *
     * @param name String
     */
    void setPresetName(String name);

    /**
     * get preset name
     *
     * @return String
     */
    String getPresetName();

    /**
     * assert preset not empty
     */
    void assertPresetDAONotEmpty();

    /**
     * assert properties not empty
     */
    void assertPropertiesNotEmpty();
}
