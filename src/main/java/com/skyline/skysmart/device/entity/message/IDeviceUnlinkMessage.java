package com.skyline.skysmart.device.entity.message;

/**
 * [FEATURE INFO]<br/>
 * device unlink message interface
 *
 * @author Skyline
 * @create 2022/7/26 13:15
 * @since 1.0.0
 */
public interface IDeviceUnlinkMessage {

    /**
     * get device id
     *
     * @return String, device id
     */
    String getDeviceId();

    /**
     * get device link auth token
     *
     * @return String, auth token
     */
    String getAuthToken();

    /**
     * get device unlink time
     *
     * @return Long, unlink time
     */
    Long getUnlinkTime();

}
