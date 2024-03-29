package com.skyline.skysmart.device.entity.bo;

import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;

import java.util.HashMap;
import java.util.Set;

/**
 * [FEATURE INFO]<br/>
 * user device relation bo interface
 *
 * @author Skyline
 * @create 2022/7/26 17:09
 * @since 1.0.0
 */
public interface IUserDeviceRelationBO {

    void mapUserDeviceRelationDAO(UserDeviceRelationDAO userDeviceRelationDAO);

    UserDeviceRelationDAO getUserDeviceRelationDAO();

    void mapUserBO(IUserBO userBO);

    IUserBO getUserBO();

    void mapDeviceBO(IDeviceBO deviceBO);

    IDeviceBO getDeviceBO();

    void setId(String id);

    String getId();

    void setUid(String uid);

    String getUid();

    void setDeviceId(String deviceId);

    String getDeviceId();

    void setAliasName(String aliasName);

    String getAliasName();

    void setProperty(String name, IProperty property);

    void setProperty(String name, String value);

    /**
     * must synchronize property when create this object or update preset or change preset
     */
    void syncProperty();

    HashMap<String, IProperty> getProperties();

    IProperty getProperty(String name);

    void setPreset(String name, HashMap<String, String> preset);

    HashMap<String, HashMap<String, String>> getPresets();

    HashMap<String, String> getPreset(String name);

    HashMap<String, String> getDefaultPreset();

    Set<String> getCustomizablePropertyId() ;

    /**
     * change the name of current preset
     *
     * @param presetName String, new name of current preset
     */
    void setCurrentPresetName(String presetName);

    /**
     * change current preset
     *
     * @param presetName String, other preset's name
     */
    void changeCurrentPreset(String presetName);

    String getCurrentPresetName();

    void assertRelationDAONotNull();

    void assertUserBONotNull();

    void assertDeviceBONotNull();
}
