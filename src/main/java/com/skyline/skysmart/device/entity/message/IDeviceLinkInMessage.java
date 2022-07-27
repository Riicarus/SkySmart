package com.skyline.skysmart.device.entity.message;

/**
 * [FEATURE INFO]<br/>
 * device link in message interface
 *
 * @author Skyline
 * @create 2022/7/26 12:38
 * @since 1.0.0
 */
public interface IDeviceLinkInMessage {

    String getDeviceId();

    String getProductId();

    String getDeviceName();

    String getIp();

    String getMac();

    String getAuthToken();

    Long getLinkInTime();

}
