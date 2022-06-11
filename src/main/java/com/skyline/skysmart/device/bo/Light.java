package com.skyline.skysmart.device.bo;

import com.skyline.skysmart.device.control.interfaces.Switchable;
import lombok.Getter;
import lombok.Setter;

/**
 * [FEATURE INFO]<br/>
 * light device
 *
 * @author Skyline
 * @create 2022/6/10 18:28
 * @since 1.0.0
 */
@Setter
@Getter
public class Light implements Switchable {

    private String deviceId;

    public Light(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * turn on device at time
     *
     * @param time Long, time
     */
    @Override
    public void on(Long time) {
        System.out.println("Light will be turned on at time: " + time);
    }

    /**
     * turn of device at time
     *
     * @param time Long, time
     */
    @Override
    public void off(Long time) {
        System.out.println("Light will be turned off at time: " + time);
    }
}
