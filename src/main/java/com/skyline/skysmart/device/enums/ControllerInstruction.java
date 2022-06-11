package com.skyline.skysmart.device.enums;

import com.skyline.skysmart.device.control.controller.LightController;
import com.skyline.skysmart.device.control.controller.ScreenController;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * instruction of controller send to DeviceControlCenter to decide which DeviceController to use
 *
 * @author Skyline
 * @create 2022/6/10 19:12
 * @since 1.0.0
 */
public enum ControllerInstruction {

    LIGHT("light", LightController.class.getSimpleName()),
    SCREEN("screen", ScreenController.class.getSimpleName());

    private final String instruction;
    private final String className;

    ControllerInstruction(String instruction, String className) {
        this.instruction = instruction;
        this.className = className;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getClassName() {
        return className;
    }

    public String getClassName(String instruction) {
        return getControllerNameMapping().get(instruction);
    }

    /**
     * get controller's instruction and classname mapping
     *
     * @return HashMap
     */
    public static HashMap<String, String> getControllerNameMapping() {
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put(LIGHT.instruction, LIGHT.className);
        mapping.put(SCREEN.instruction, SCREEN.className);

        return mapping;
    }
}
