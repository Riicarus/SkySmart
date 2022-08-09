package com.skyline.skysmart.device.entity.message.impl;

import com.skyline.skysmart.device.entity.message.IDeviceRegisterMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * [FEATURE INFO]<br/>
 * device register message
 *
 * @author Skyline
 * @create 2022/7/26 21:20
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeviceRegisterMessage implements IDeviceRegisterMessage {

    private String deviceId;
    private String ip;
    private String mac;
    private String gatewayDeviceId;
    private String authToken;
    private Long registerTime;

    @Override
    public String getDeviceId() {
        return deviceId;
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
    public String getGatewayDeviceId() {
        return gatewayDeviceId;
    }

    @Override
    public String getAuthToken() {
        return authToken;
    }

    @Override
    public Long getRegisterTime() {
        return registerTime;
    }
}
