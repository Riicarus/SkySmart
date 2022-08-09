package com.skyline.skysmart.device.service;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device data service interface <br/>
 * device get data from here
 *
 * @author Skyline
 * @create 2022-8-9 18:28
 * @since 1.0.0
 */
public interface IDeviceDataService {

    /**
     * list all sensor's data
     *
     * @param gatewayId String, deviceId
     * @return HashMap, key: deviceId + propertyId, value: propertyValue
     */
    HashMap<String, String> listAllSensorData(String gatewayId);

}
