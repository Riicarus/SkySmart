package com.skyline.skysmart.device.service;

import java.util.ArrayList;
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
     * @return HashMap, key_outer: deviceId, key_inner: propertyId, value: propertyValue
     */
    HashMap<String, HashMap<String, String>> listAllSensorData(String gatewayId);

    /**
     * list all sensors' info, like: deviceId-aliasName-propertyIds
     *
     * @param gatewayId String, deviceId of gateway
     * @return ArrayList
     */
    ArrayList<String> listAllSensorInfo(String gatewayId);

}
