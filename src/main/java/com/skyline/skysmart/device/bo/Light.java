package com.skyline.skysmart.device.bo;

import com.skyline.skysmart.device.control.interfaces.Switchable;
import com.skyline.skysmart.device.data.LightProperties;
import com.skyline.skysmart.web.dao.LightDAO;
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
public class Light implements Switchable {

    private LightDAO lightDAO;
    private LightProperties properties;

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
