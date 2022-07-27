package com.skyline.skysmart.device.entity.model;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * product type of device
 *
 * @author Skyline
 * @create 2022/7/26 10:58
 * @since 1.0.0
 */
public interface IProduct {

    /**
     * get product id
     *
     * @return String, product id
     */
    String getId();

    void setId(String id);

    /**
     * get product name
     *
     * @return String, product name
     */
    String getName();

    void setName(String name);

    /**
     * get product type
     *
     * @return String, type
     */
    String getType();

    void setType(String type);

    /**
     * get produce time
     *
     * @return Long, produce time
     */
    Long getProducedTime();

    void setProducedTime(Long producedTime);

    /**
     * default properties in this product
     *
     * @return ArrayList, default properties
     */
    ArrayList<IProperty> getProperties();

    void setProperties(ArrayList<IProperty> properties);

    void addProperty(IProperty property);
}
