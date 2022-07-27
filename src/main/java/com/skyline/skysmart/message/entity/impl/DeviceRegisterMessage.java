package com.skyline.skysmart.message.entity.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.message.entity.IDeviceRegisterMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

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
    private String deviceName;
    private String productId;
    private String ip;
    private String mac;
    private String authToken;
    private Long registerTime;
    private String properties;

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public String getProductId() {
        return productId;
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
    public ArrayList<IProperty> getProperties() {
        return JSONObject.parseObject(properties, new TypeReference<ArrayList<IProperty>>(){});
    }

    @Override
    public Long getRegisterTime() {
        return registerTime;
    }
}
