package com.skyline.skysmart.device.data.devices;

import com.skyline.skysmart.device.data.devices.interfaces.ILightDevice;
import lombok.*;

/**
 * [FEATURE INFO]<br/>
 * light device
 *
 * @author Skyline
 * @create 2022/6/10 18:28
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LightDevice extends Device implements ILightDevice {

    /**
     * turn on device at time
     *
     * @param time Long, time
     */
    @Override
    public void on(Long time) {
        System.out.println("LightDevice will be turned on at time: " + time);
    }

    /**
     * turn of device at time
     *
     * @param time Long, time
     */
    @Override
    public void off(Long time) {
        System.out.println("LightDevice will be turned off at time: " + time);
    }
}
