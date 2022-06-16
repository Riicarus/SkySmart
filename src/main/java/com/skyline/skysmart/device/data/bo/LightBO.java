package com.skyline.skysmart.device.data.bo;

import com.skyline.skysmart.device.data.bo.interfaces.ILightBO;
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
@AllArgsConstructor
@NoArgsConstructor
public class LightBO implements ILightBO {

    private Light light;
    private LightProperties properties;

    /**
     * turn on device at time
     *
     * @param time Long, time
     */
    @Override
    public void on(Long time) {
        System.out.println("LightBO will be turned on at time: " + time);
    }

    /**
     * turn of device at time
     *
     * @param time Long, time
     */
    @Override
    public void off(Long time) {
        System.out.println("LightBO will be turned off at time: " + time);
    }
}
