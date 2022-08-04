package com.skyline.skysmart.device.entity.model;

import com.skyline.skysmart.device.entity.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * common property
 *
 * @author Skyline
 * @create 2022/7/27 9:05
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class CommonProperty implements IProperty {

    private String id;
    private String name;
    private PropertyType propertyType;
    private IValueType valueType;
    private String value;
    private HashMap<String, String> expands;

    /**
     * get property id, like: cup_usage
     *
     * @return String, property id
     */
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public PropertyType getPropertyType() {
        return this.propertyType;
    }

    @Override
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * get property detail description
     *
     * @return String, property detail
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get property value type
     *
     * @return IValueType
     */
    @Override
    public IValueType getValueType() {
        return valueType;
    }

    @Override
    public void setValueType(IValueType valueType) {
        this.valueType = valueType;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * may have some expanded value
     *
     * @return HashMap, expanded values
     */
    @Override
    public HashMap<String, String> getExpands() {
        return expands;
    }

    @Override
    public void setExpands(HashMap<String, String> expands) {
        this.expands = expands;
    }

}
