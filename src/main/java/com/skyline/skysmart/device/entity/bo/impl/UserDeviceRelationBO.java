package com.skyline.skysmart.device.entity.bo.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.ApiException;
import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * user device relation bo
 *
 * @author Skyline
 * @create 2022/7/26 20:32
 * @since 1.0.0
 */
public class UserDeviceRelationBO implements IUserDeviceRelationBO {

    private UserDeviceRelationDAO userDeviceRelationDAO;
    private IUserBO userBO;
    private IDeviceBO deviceBO;

    @Override
    public void mapUserDeviceRelationDAO(UserDeviceRelationDAO userDeviceRelationDAO) {
        this.userDeviceRelationDAO = userDeviceRelationDAO;
    }

    @Override
    public UserDeviceRelationDAO getUserDeviceRelationDAO() {
        assertRelationDAONotNull();
        return this.userDeviceRelationDAO;
    }

    @Override
    public void mapUserBO(IUserBO userBO) {
        this.userBO = userBO;
    }

    @Override
    public IUserBO getUserBO() {
        assertUserBONotNull();
        return this.userBO;
    }

    @Override
    public void mapDeviceBO(IDeviceBO deviceBO) {
        this.deviceBO = deviceBO;
    }

    @Override
    public IDeviceBO getDeviceBO() {
        assertDeviceBONotNull();
        return this.deviceBO;
    }

    @Override
    public void setId(String id) {
        assertRelationDAONotNull();
        this.userDeviceRelationDAO.setId(id);
    }

    @Override
    public String getId() {
        assertRelationDAONotNull();
        return this.userDeviceRelationDAO.getId();
    }

    @Override
    public void setUid(String uid) {
        assertRelationDAONotNull();

        this.userDeviceRelationDAO.setUid(uid);
    }

    @Override
    public String getUid() {
        assertRelationDAONotNull();

        return this.userDeviceRelationDAO.getUid();
    }

    @Override
    public void setDeviceId(String deviceId) {
        assertDeviceBONotNull();
        this.userDeviceRelationDAO.setDeviceId(deviceId);
    }

    @Override
    public String getDeviceId() {
        assertDeviceBONotNull();
        return this.userDeviceRelationDAO.getDeviceId();
    }

    @Override
    public void setAliasName(String aliasName) {
        assertRelationDAONotNull();
        this.userDeviceRelationDAO.setAliasName(aliasName);
    }

    @Override
    public String getAliasName() {
        assertRelationDAONotNull();
        return this.userDeviceRelationDAO.getAliasName();
    }

    @Override
    public void setPresets(HashMap<String, ArrayList<IProperty>> presets) {
        assertRelationDAONotNull();
        this.userDeviceRelationDAO.setPresets(JSON.toJSONString(presets));
    }

    @Override
    public void addPreset(String name, ArrayList<IProperty> preset) {
        HashMap<String, ArrayList<IProperty>> presets = getPresets();
        presets.put(name, preset);

        setPresets(presets);
    }

    @Override
    public HashMap<String, ArrayList<IProperty>> getPresets() {
        assertRelationDAONotNull();

        String json = this.userDeviceRelationDAO.getPresets();

        return JSONObject.parseObject(json, new TypeReference<HashMap<String, ArrayList<IProperty>>>(){});
    }

    @Override
    public ArrayList<IProperty> getPreset(String name) {
        return getPresets().get(name);
    }

    @Override
    public void setCurrentPresetName(String presetName) {
        assertRelationDAONotNull();
        this.userDeviceRelationDAO.setCurrentPresetName(presetName);
    }

    @Override
    public String getCurrentPresetName() {
        assertRelationDAONotNull();
        return this.userDeviceRelationDAO.getCurrentPresetName();
    }

    @Override
    public void assertRelationDAONotNull() {
        if (this.userDeviceRelationDAO == null) {
            throw new ApiException(ResultCode.NULL);
        }
    }

    @Override
    public void assertUserBONotNull() {
        if (this.userBO == null) {
            throw new ApiException(ResultCode.NULL);
        }
    }

    @Override
    public void assertDeviceBONotNull() {
        if (this.deviceBO == null) {
            throw new ApiException(ResultCode.NULL);
        }
    }
}
