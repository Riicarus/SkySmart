package com.skyline.skysmart.device.data.devices;

import com.skyline.skysmart.device.data.devices.interfaces.IScreenDevice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * screen device
 *
 * @author Skyline
 * @create 2022/6/10 23:15
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ScreenDevice extends Device implements IScreenDevice {

    /**
     * turn on device at time
     *
     * @param time Long, time
     */
    @Override
    public void on(Long time) {

    }

    /**
     * turn of device at time
     *
     * @param time Long, time
     */
    @Override
    public void off(Long time) {

    }
}
