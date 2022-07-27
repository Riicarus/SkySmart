package com.skyline.skysmart.device.entity.message;

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

    Long getRegisterTime();

}
