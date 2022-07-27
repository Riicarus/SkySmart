package com.skyline.skysmart.device.entity.bo.impl;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.ApiException;
import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.dao.DeviceDAO;
import com.skyline.skysmart.device.entity.model.IProperty;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * device bo
 *
 * @author Skyline
 * @create 2022/7/26 17:34
 * @since 1.0.0
 */
public class DeviceBO implements IDeviceBO {

    private DeviceDAO deviceDAO;

    private IProductBO productBO;

    private String ip;

    private String mac;

    private String authToken;

    @Override
    public void mapDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @Override
    public DeviceDAO getDeviceDAO() {
        assertDeviceDAONotNull();
        return this.deviceDAO;
    }

    @Override
    public void mapProductBO(IProductBO productBO) {
        this.productBO = productBO;
    }

    @Override
    public IProductBO getProductBO() {
        assertProductBONotNull();
        return this.productBO;
    }

    @Override
    public void setCreateTime(Long createTime) {
        assertDeviceDAONotNull();
        this.deviceDAO.setCreateTime(createTime);
    }

    @Override
    public Long getCreateTime() {
        assertDeviceDAONotNull();
        return this.deviceDAO.getCreateTime();
    }

    @Override
    public void setLastRegisterTime(Long lastRegisterTime) {
        assertDeviceDAONotNull();
        this.deviceDAO.setLastRegisterTime(lastRegisterTime);
    }

    @Override
    public Long getLastRegisterTime() {
        assertDeviceDAONotNull();
        return this.deviceDAO.getLastRegisterTime();
    }

    @Override
    public void assertDeviceDAONotNull() {
        if (this.deviceDAO == null) {
            throw new ApiException(ResultCode.NULL);
        }
    }

    @Override
    public void assertProductBONotNull() {
        if (this.productBO == null) {
            throw new ApiException(ResultCode.NULL);
        }
    }

    /**
     * device id
     *
     * @return String, device id
     */
    @Override
    public String getId() {
        assertDeviceDAONotNull();
        return this.deviceDAO.getId();
    }

    @Override
    public void setId(String id) {
        assertDeviceDAONotNull();
        this.deviceDAO.setId(id);
    }

    /**
     * product id of device
     *
     * @return String product id
     */
    @Override
    public String getProductId() {
        assertProductBONotNull();
        return this.productBO.getId();
    }

    @Override
    public void setProductId(String productId) {
        assertProductBONotNull();
        this.productBO.setId(productId);
    }

    /**
     * device name
     *
     * @return String, device name
     */
    @Override
    public String getName() {
        assertDeviceDAONotNull();
        return this.deviceDAO.getName();
    }

    @Override
    public void setName(String name) {
        assertDeviceDAONotNull();
        this.deviceDAO.setName(name);
    }

    /**
     * device ip
     *
     * @return String, device ip
     */
    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * device mac address
     *
     * @return String, mac address
     */
    @Override
    public String getMac() {
        return this.mac;
    }

    @Override
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * device auth token, used to verify device
     *
     * @return String auth token
     */
    @Override
    public String getAuthToken() {
        return this.authToken;
    }

    @Override
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * device properties of now time
     *
     * @return ArrayList, device properties
     */
    @Override
    public ArrayList<IProperty> getProperties() {
        assertProductBONotNull();

        return this.productBO.getProperties();
    }

    @Override
    public void setProperties(ArrayList<IProperty> properties) {
        assertProductBONotNull();

        this.productBO.setProperties(properties);
    }
}
