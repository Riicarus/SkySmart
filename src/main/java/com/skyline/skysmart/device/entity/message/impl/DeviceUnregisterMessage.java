package com.skyline.skysmart.device.entity.message.impl;

import com.skyline.skysmart.device.entity.message.IDeviceUnregisterMessage;
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
    private Long unregisterTime;
    private String authToken;

    @Override
    public String getDeviceId() {
        return deviceId;
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
