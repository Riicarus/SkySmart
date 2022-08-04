package com.skyline.skysmart.device.entity.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * common value type
 *
 * @author Skyline
 * @create 2022/7/27 9:11
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class CommonValueType implements IValueType {

    private String type;
    private String maxValue;
    private String minValue;
    private String unit;
    HashMap<String, String> expands;

    /**
     * value type name
     *
     * @return String, value type
     */
    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * max value of this value type
     *
     * @return String, max value
     */
    @Override
    public String getMaxValue() {
        return maxValue;
    }

    @Override
    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * min value of this value type
     *
     * @return String, min value
     */
    @Override
    public String getMinValue() {
        return minValue;
    }

    @Override
    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    /**
     * unit of this value type
     *
     * @return String, unit
     */
    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get expanded value map
     *
     * @return HashMap, expanded value
     */
    @Override
    public HashMap<String, String> getExpands() {
        return expands;
    }

    /**
     * value type has some expanded values
     *
     * @param expands HashMap, expanded value
     */
    @Override
    public void setExpands(HashMap<String, String> expands) {
        this.expands = expands;
    }
}
