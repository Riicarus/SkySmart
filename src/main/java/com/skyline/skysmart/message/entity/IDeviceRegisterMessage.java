package com.skyline.skysmart.message.entity;

import com.skyline.skysmart.device.entity.model.IProperty;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * device register message interface
 *
 * @author Skyline
 * @create 2022/7/26 16:01
 * @since 1.0.0
 */
public interface IDeviceRegisterMessage {

    String getDeviceId();

    String getDeviceName();

    String getProductId();

    String getIp();

    String getMac();

    String getAuthToken();

    ArrayList<IProperty> getProperties();

    Long getRegisterTime();

}
