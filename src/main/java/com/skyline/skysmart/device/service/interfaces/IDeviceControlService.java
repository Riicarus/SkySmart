package com.skyline.skysmart.device.service.interfaces;

import com.skyline.skysmart.device.data.dto.DeviceInternetInfo;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device control service interface
 *
 * @author Skyline
 * @create 2022/6/19 23:31
 * @since 1.0.0
 */
public interface IDeviceControlService {

    /**
     * register device, save device's internet information to redis
     *
     * @param info DeviceInternetInfo
     */
    void register(DeviceInternetInfo info);

    /**
     * turn on service to work status at the setting time
     *
     * @param deviceId String
     * @param time Long, timestamp
     */
    void turnOn(String deviceId, Long time);

    /**
     * turn off service to sleep status at the setting time
     *
     * @param deviceId String
     * @param time Long, timestamp
     */
    void turnOff(String deviceId, Long time);

    /**
     * handle multi instructions
     *
     * @param deviceId String
     * @param params HashMap, contains operations and their param
     */
    void handleMulti(String deviceId, HashMap<String, String> params);

}
