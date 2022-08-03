package com.skyline.skysmart.device.entity.message;

/**
 * [FEATURE INFO]<br/>
 * device unregister message interface
 *
 * @author Skyline
 * @create 2022/7/26 16:08
 * @since 1.0.0
 */
public interface IDeviceUnregisterMessage {

    String getDeviceId();

    Long getUnregisterTime();

    String getAuthToken();

}
