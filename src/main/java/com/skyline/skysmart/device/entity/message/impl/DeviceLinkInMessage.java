package com.skyline.skysmart.device.entity.message.impl;

import com.skyline.skysmart.device.entity.message.IDeviceLinkInMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * [FEATURE INFO]<br/>
 * device link in message
 *
 * @author Skyline
 * @create 2022/7/26 21:15
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeviceLinkInMessage implements IDeviceLinkInMessage {

    private String deviceId;
    private String productId;
    private String deviceName;
    private String ip;
    private String mac;
    private String authToken;
    private Long linkInTime;

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public String getMac() {
        return mac;
    }

    @Override
    public String getAuthToken() {
        return authToken;
    }

    @Override
    public Long getLinkInTime() {
        return linkInTime;
    }
}
