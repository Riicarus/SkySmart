package com.skyline.skysmart.device.entity.model;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * property interface
 *
 * @author Skyline
 * @create 2022/7/26 11:03
 * @since 1.0.0
 */
public interface IProperty {

    /**
     * get property id, like: cup_usage
     *
     * @return String, property id
     */
    String getId();

    void setId(String id);

    /**
     * get property detail description
     *
     * @return String, property detail
     */
    String getName();

    void setName(String name);

    /**
     * get property value type
     *
     * @return IValueType
     */
    IValueType getValueType();

    void setValueType(IValueType valueType);

    /**
     * may have some expanded value
     *
     * @return HashMap, expanded values
     */
    HashMap<String, String> getExpands();

    void setExpands(HashMap<String, String> expands);

}
