package com.skyline.skysmart.device.entity.bo;

import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;

import java.util.ArrayList;
import java.util.HashMap;

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

    void setPresets(HashMap<String, ArrayList<IProperty>> presets);

    void addPreset(String name, ArrayList<IProperty> preset);

    HashMap<String, ArrayList<IProperty>> getPresets();

    ArrayList<IProperty> getPreset(String name);

    void setCurrentPresetName(String presetName);

    String getCurrentPresetName();

    void assertRelationDAONotNull();

    void assertUserBONotNull();

    void assertDeviceBONotNull();
}
