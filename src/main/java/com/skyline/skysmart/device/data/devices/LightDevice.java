package com.skyline.skysmart.device.data.devices;

import com.skyline.skysmart.device.data.devices.interfaces.ILightDevice;
import com.skyline.skysmart.device.data.dao.Light;
import com.skyline.skysmart.device.data.po.LightProperties;
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
@AllArgsConstructor
@NoArgsConstructor
public class LightDevice extends Device implements ILightDevice {

    private Light light;
    private LightProperties properties;

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
