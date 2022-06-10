package com.skyline.skysmart.device.control;

import com.skyline.skysmart.device.control.bo.Light;
import org.springframework.stereotype.Component;

/**
 * [FEATURE INFO]<br/>
 * light control object
 *
 * @author Skyline
 * @create 2022/6/10 15:46
 * @since 1.0.0
 */
@Component("LightController")
public class LightController extends DeviceController {

    /**
     * execute method according to instruction
     *
     * @param instruction String
     */
    @Override
    public void execute(String instruction) {
        // todo determine which method should be executed by instruction
        System.out.println(instruction);
    }

    public void turnOn(Light light) {
        light.on();
    }

    public void turnOff(Light light) {
        light.off();
    }

}
