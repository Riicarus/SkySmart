package com.skyline.skysmart.device.entity.bo.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.ApiException;
import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import com.skyline.skysmart.device.entity.model.IProperty;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * product bo
 *
 * @author Skyline
 * @create 2022/7/26 20:25
 * @since 1.0.0
 */
public class ProductBO implements IProductBO {

    private ProductDAO productDAO;

    @Override
    public void mapProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        assertProductDAONotNull();
        return this.productDAO;
    }

    @Override
    public void assertProductDAONotNull() {
        if (this.productDAO == null) {
            throw new ApiException(ResultCode.NULL);
        }
    }

    /**
     * get product id
     *
     * @return String, product id
     */
    @Override
    public String getId() {
        assertProductDAONotNull();
        return this.productDAO.getId();
    }

    @Override
    public void setId(String id) {
        assertProductDAONotNull();
        this.productDAO.setId(id);
    }

    /**
     * get product name
     *
     * @return String, product name
     */
    @Override
    public String getName() {
        assertProductDAONotNull();
        return this.productDAO.getName();
    }

    @Override
    public void setName(String name) {
        assertProductDAONotNull();
        this.productDAO.setName(name);
    }

    /**
     * get product type
     *
     * @return String, type
     */
    @Override
    public String getType() {
        assertProductDAONotNull();
        return this.productDAO.getType();
    }

    @Override
    public void setType(String type) {
        assertProductDAONotNull();
        this.productDAO.setType(type);
    }

    /**
     * get produce time
     *
     * @return Long, produce time
     */
    @Override
    public Long getProducedTime() {
        assertProductDAONotNull();
        return this.productDAO.getProducedTime();
    }

    @Override
    public void setProducedTime(Long producedTime) {
        assertProductDAONotNull();
        this.productDAO.setProducedTime(producedTime);
    }

    /**
     * default properties in this product
     *
     * @return ArrayList, default properties
     */
    @Override
    public ArrayList<IProperty> getProperties() {
        assertProductDAONotNull();

        String json = this.productDAO.getDefaultProperties();

        return JSONObject.parseObject(json, new TypeReference<ArrayList<IProperty>>(){});
    }

    @Override
    public void setProperties(ArrayList<IProperty> properties) {
        assertProductDAONotNull();
        this.productDAO.setDefaultProperties(JSON.toJSONString(properties));
    }

    @Override
    public void addProperty(IProperty property) {
        ArrayList<IProperty> properties = getProperties();
        properties.add(property);
        setProperties(properties);
    }
}
