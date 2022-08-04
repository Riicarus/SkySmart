package com.skyline.skysmart.device.entity.bo.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.ApiException;
import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.enums.PropertyType;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public void setProperty(String name, IProperty property) {
        assertRelationDAONotNull();

        HashMap<String, IProperty> properties = getProperties();
        properties.put(name, property);
        this.userDeviceRelationDAO.setProperties(JSONObject.toJSONString(properties));
    }

    @Override
    public void setProperty(String name, String value) {
        IProperty property = getProperty(name);
        if (property == null) {
            throw new ApiException(ResultCode.NULL);
        }

        property.setValue(value);
        setProperty(name, property);
    }

    /**
     * must synchronize property when create this object or update preset or change preset
     */
    @Override
    public void syncProperty() {
        String currentPresetName = userDeviceRelationDAO.getCurrentPresetName();
        HashMap<String, String> currentPreset = getPresets().get(currentPresetName);

        currentPreset.forEach(this::setProperty);
    }

    @Override
    public HashMap<String, IProperty> getProperties() {
        assertRelationDAONotNull();

        String properties = this.userDeviceRelationDAO.getProperties();
        HashMap<String, IProperty> propertyMap = JSONObject.parseObject(properties, new TypeReference<HashMap<String, IProperty>>(){});

        if (propertyMap.isEmpty() && "default".equals(getCurrentPresetName())) {
            return this.deviceBO.getProductBO().getPropertyMap();
        }

        return propertyMap;
    }

    @Override
    public IProperty getProperty(String name) {
        return getProperties().get(name);
    }

    @Override
    public void setPreset(String name, HashMap<String, String> preset) {
        // ensure the preset is only in the range of customizable properties
        Set<String> customizablePropertyId = getCustomizablePropertyId();
        for (Map.Entry<String, String> entry : preset.entrySet()) {
            if (!customizablePropertyId.contains(entry.getKey())) {
                preset.remove(entry.getKey());
            }
        }

        HashMap<String, HashMap<String, String>> presets = getPresets();
        presets.put(name, preset);
        this.userDeviceRelationDAO.setPresets(JSONObject.toJSONString(presets));

        // if change the current used preset, change the properties too
        if (name.equals(userDeviceRelationDAO.getCurrentPresetName())) {
            preset.forEach(this::setProperty);
        }
    }

    @Override
    public HashMap<String, HashMap<String, String>> getPresets() {
        assertRelationDAONotNull();

        String json = this.userDeviceRelationDAO.getPresets();

        HashMap<String, HashMap<String, String>> presets =
                JSONObject.parseObject(json, new TypeReference<HashMap<String, HashMap<String, String>>>() {});
        presets.put("default", getDefaultPreset());
        return presets;
    }

    @Override
    public HashMap<String, String> getPreset(String name) {
        return getPresets().get(name);
    }

    @Override
    public HashMap<String, String> getDefaultPreset() {
        HashMap<String, String> defaultPreset = new HashMap<>();

        HashMap<String, IProperty> defaultProperties = getDeviceBO().getPropertyMap();
        for (Map.Entry<String, IProperty> entry : defaultProperties.entrySet()) {
            if (entry.getValue().getPropertyType().equals(PropertyType.ALL)) {
                defaultPreset.put(entry.getKey(), entry.getValue().getValue());
            }
        }

        return defaultPreset;
    }

    @Override
    public Set<String> getCustomizablePropertyId() {
        return getDefaultPreset().keySet();
    }

    /**
     * change the name of current preset
     *
     * @param presetName String, new name of current preset
     */
    @Override
    public void setCurrentPresetName(String presetName) {
        assertRelationDAONotNull();

        String oldCurrentPresetName = getCurrentPresetName();
        HashMap<String, HashMap<String, String>> presets = getPresets();
        HashMap<String, String> currentPreset = presets.get(oldCurrentPresetName);
        presets.remove(oldCurrentPresetName);
        presets.put(presetName, currentPreset);

        this.userDeviceRelationDAO.setCurrentPresetName(presetName);
    }

    /**
     * change current preset
     *
     * @param presetName String, other preset's name
     */
    @Override
    public void changeCurrentPreset(String presetName) {
        assertRelationDAONotNull();
        this.userDeviceRelationDAO.setCurrentPresetName(presetName);

        syncProperty();
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
