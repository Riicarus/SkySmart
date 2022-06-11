package com.skyline.skysmart.device.control.controller;

import com.skyline.skysmart.device.bo.Light;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

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
        System.out.println("LightController received one instruction: " + instruction);
        LinkedHashMap<String, String> params = InstructionUtils.parse(instruction);

        String deviceId = params.get("deviceId");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            executeOperation(entry, new Light(deviceId));
        }
    }

    /**
     * handle method execution
     *
     * @param entry Map.Entry, operation key and value
     * @param light Light
     */
    public void executeOperation(Map.Entry<String, String> entry, Light light) {
        String operation = entry.getKey();
        String value = entry.getValue();
        if (operation.equals("on")) {
            turnOn(light, Long.parseLong(value));
        } else if (operation.equals("off")) {
            turnOff(light, Long.parseLong(value));
        }
    }

    public void turnOn(Light light, Long time) {
        light.on(time);
    }

    public void turnOff(Light light, Long time) {
        light.off(time);
    }

}
