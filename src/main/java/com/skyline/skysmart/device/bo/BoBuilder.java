package com.skyline.skysmart.device.bo;

import org.springframework.stereotype.Component;

/**
 * [FEATURE INFO]<br/>
 * Convert data to BO
 *
 * @author Skyline
 * @create 2022/6/11 20:17
 * @since 1.0.0
 */
@Component
public class BoBuilder {

    // todo add mappers here

    public Light buildLight(String deviceId) {
        return new Light();
    }

    public Screen buildScreen(String deviceId) {
        return new Screen();
    }

}
