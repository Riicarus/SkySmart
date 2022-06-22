package com.skyline.skysmart.device.data.bo.interfaces;

import com.skyline.skysmart.device.data.bo.impls.DeviceBO;
import com.skyline.skysmart.device.data.bo.impls.PresetBO;
import com.skyline.skysmart.device.data.dao.DeviceUserRelationDAO;
import com.skyline.skysmart.device.data.dto.InstructionUnit;

import java.util.HashMap;
import java.util.Queue;

/**
 * [FEATURE INFO]<br/>
 * DeviceUserRelationBO
 *
 * @author Skyline
 * @create 2022/6/17 11:49
 * @since 1.0.0
 */
public interface IDeviceUserRelationBO {

    /**
     * set device user relation dao
     *
     * @param deviceUserRelationDAO DeviceUserRelationDAO
     */
    void mapDeviceUserRelationDAO(DeviceUserRelationDAO deviceUserRelationDAO);

    /**
     * get device user relation dao
     *
     * @return DeviceUserRelationDAO
     */
    DeviceUserRelationDAO getDeviceUserRelationDAO();

    /**
     * set device info
     *
     * @param deviceBO DeviceBO
     */
    void setDeviceInfo(DeviceBO deviceBO);

    /**
     * set preset info
     *
     * @param presetBO PresetBO
     */
    void setDevicePreset(PresetBO presetBO);

    /**
     * set all preset infos
     *
     * @param presetMap HashMap
     */
    void setDevicePreset(HashMap<String, Queue<InstructionUnit>> presetMap);

    /**
     * assert device user relation not empty
     */
    void assertRelationNotEmpty();
}
