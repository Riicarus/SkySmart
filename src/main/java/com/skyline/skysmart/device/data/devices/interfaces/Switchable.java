package com.skyline.skysmart.device.data.devices.interfaces;

/**
 * [FEATURE INFO]<br/>
 * devices with can be switched should implement this interface
 *
 * @author Skyline
 * @create 2022/6/10 15:12
 * @since 1.0.0
 */
public interface Switchable {

    /**
     * turn on device at time
     *
     * @param time Long, time
     */
    void on(Long time);

    /**
     * turn of device at time
     *
     * @param time Long, time
     */
    void off(Long time);

}
