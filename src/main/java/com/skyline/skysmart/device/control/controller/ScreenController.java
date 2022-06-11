package com.skyline.skysmart.device.control.controller;

import com.skyline.skysmart.device.bo.Screen;
import org.springframework.stereotype.Component;

/**
 * [FEATURE INFO]<br/>
 * screen controller
 *
 * @author Skyline
 * @create 2022/6/10 23:14
 * @since 1.0.0
 */
@Component("ScreenController")
public class ScreenController extends DeviceController {

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

    public void turnOn(Screen screen, Long time) {
        screen.on(time);
    }

    public void turnOff(Screen screen, Long time) {
        screen.off(time);
    }
}
