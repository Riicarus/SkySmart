package com.skyline.skysmart.device.entity.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * function interface
 *
 * @author Skyline
 * @create 2022/7/26 11:02
 * @since 1.0.0
 */
public interface IFunction {

    /**
     * get function id, like: get_cpu_usage
     *
     * @return String, function id
     */
    String getId();

    void setId(String id);

    /**
     * get function detail description
     *
     * @return String, function detail
     */
    String getName();

    void setName(String name);

    /**
     * get all properties of this function
     *
     * @return ArrayList, all properties
     */
    ArrayList<IProperty> getProperties();

    /**
     * one function contains some properties, to describe device's properties change
     *
     * @param properties ArrayList, all properties
     */
    void setProperties(ArrayList<IProperty> properties);

    void addProperty(IProperty property);

    /**
     * may have some expanded value
     *
     * @return HashMap, expanded values
     */
    HashMap<String, String> getExpands();

    void setExpands(HashMap<String, String> expands);


}
