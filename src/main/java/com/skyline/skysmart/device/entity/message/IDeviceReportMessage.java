package com.skyline.skysmart.device.entity.message;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device report message interface
 *
 * @author Skyline
 * @create 2022-8-4 20:49
 * @since 1.0.0
 */
public interface IDeviceReportMessage {

    HashMap<String, String> getDataMap();

    String getData(String propertyId);

    String getDeviceId();
}
