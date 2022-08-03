package com.skyline.skysmart.device.entity.model;

import com.skyline.skysmart.device.entity.enums.PropertyType;

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
     * determine whether it could be edit by user, or just the data device reported
     *
     * @return PropertyType
     */
    PropertyType getPropertyType();

    void setPropertyType(PropertyType propertyType);

    /**
     * get property detail description
     *
     * @return String, property detail
     */
    String getName();

    void setName(String name);

    String getValue();

    void setValue(String value);

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
