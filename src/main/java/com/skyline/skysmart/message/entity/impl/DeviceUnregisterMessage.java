package com.skyline.skysmart.message.entity.impl;

import com.skyline.skysmart.message.entity.IDeviceUnregisterMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * [FEATURE INFO]<br/>
 * device unregister message
 *
 * @author Skyline
 * @create 2022/7/27 10:11
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeviceUnregisterMessage implements IDeviceUnregisterMessage {

    private String deviceId;
    private String deviceName;
    private Long unregisterTime;
    private String authToken;

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public Long getUnregisterTime() {
        return unregisterTime;
    }

    @Override
    public String getAuthToken() {
        return authToken;
    }
}
