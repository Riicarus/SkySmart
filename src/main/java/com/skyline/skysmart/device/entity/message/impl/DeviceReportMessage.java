package com.skyline.skysmart.device.entity.message.impl;

import com.skyline.skysmart.device.entity.message.IDeviceReportMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device report message
 *
 * @author Skyline
 * @create 2022-8-4 21:34
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeviceReportMessage implements IDeviceReportMessage {

    private String deviceId;

    private HashMap<String, String> dataMap;

    @Override
    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

    @Override
    public String getData(String propertyId) {
        return getDataMap().get(propertyId);
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }
}
