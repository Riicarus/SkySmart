package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.dao.DeviceDAO;

/**
 * [FEATURE INFO]<br/>
 * device service
 *
 * @author Skyline
 * @create 2022/7/26 22:56
 * @since 1.0.0
 */
public interface IDeviceDBService {

    DeviceDAO getDeviceDAO(String deviceId);

    IDeviceBO getDeviceBO(String deviceId);

    void updateLastRegisterTime(String deviceId, Long lastRegisterTime);

}
