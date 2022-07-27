package com.skyline.skysmart.device.entity.model;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * value type interface
 *
 * @author Skyline
 * @create 2022/7/26 11:25
 * @since 1.0.0
 */
public interface IValueType {

    /**
     * value type name
     *
     * @return String, value type
     */
    String getType();

    void setType(String type);

    /**
     * max value of this value type
     *
     * @return String, max value
     */
    String getMaxValue();

    void setMaxValue(String maxValue);

    /**
     * min value of this value type
     *
     * @return String, min value
     */
    String getMinValue();

    void setMinValue(String minValue);

    /**
     * unit of this value type
     *
     * @return String, unit
     */
    String getUnit();

    void setUnit(String unit);

    /**
     * get expanded value map
     *
     * @return HashMap, expanded value
     */
    HashMap<String, String> getExpands();

    /**
     * value type has some expanded values
     *
     * @param expands HashMap, expanded value
     */
    void setExpands(HashMap<String, String> expands);

}
