package com.skyline.skysmart.device.data.bo.impls;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.interfaces.IDeviceUserRelationBO;
import com.skyline.skysmart.device.data.dao.DeviceDAO;
import com.skyline.skysmart.device.data.dao.DeviceUserRelationDAO;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device user relation bo
 *
 * @author Skyline
 * @create 2022/6/17 11:58
 * @since 1.0.0
 */
public class DeviceUserRelationBO implements IDeviceUserRelationBO {

    private DeviceUserRelationDAO deviceUserRelationDAO;
    private HashMap<String, HashMap<String, String>> presetMap = new HashMap<>();
    private DeviceDAO deviceDAO;

    /**
     * set device user relation dao
     *
     * @param deviceUserRelationDAO DeviceUserRelationDAO
     */
    @Override
    public void mapDeviceUserRelationDAO(DeviceUserRelationDAO deviceUserRelationDAO) {
        this.deviceUserRelationDAO = deviceUserRelationDAO;
    }

    /**
     * get device user relation dao
     *
     * @return DeviceUserRelationDAO
     */
    @Override
    public DeviceUserRelationDAO getDeviceUserRelationDAO() {
        assertRelationNotEmpty();
        return this.deviceUserRelationDAO;
    }

    /**
     * set device info
     *
     * @param deviceBO DeviceBO
     */
    @Override
    public void setDeviceInfo(DeviceBO deviceBO) {
        this.deviceDAO = deviceBO.getDeviceDAO();
    }

    /**
     * set preset info
     *
     * @param presetBO PresetBO
     */
    @Override
    public void setDevicePreset(PresetBO presetBO) {
        this.presetMap.put(presetBO.getPresetName(), presetBO.getProperties());
    }

    /**
     * set all preset infos
     *
     * @param presetMap HashMap
     */
    @Override
    public void setDevicePreset(HashMap<String, HashMap<String, String>> presetMap) {
        this.presetMap = presetMap;
    }

    /**
     * assert device user relation not empty
     */
    @Override
    public void assertRelationNotEmpty() {
        if (this.deviceUserRelationDAO == null) {
            Asserts.fail(ResultCode.NULL);
        }
    }
}
