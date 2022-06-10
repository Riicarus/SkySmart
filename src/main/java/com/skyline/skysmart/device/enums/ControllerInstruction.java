package com.skyline.skysmart.device.enums;

import com.skyline.skysmart.device.control.LightController;

/**
 * [FEATURE INFO]<br/>
 * instruction of controller send to DeviceControlCenter to decide which DeviceController to use
 *
 * @author Skyline
 * @create 2022/6/10 19:12
 * @since 1.0.0
 */
public enum ControllerInstruction {

    LIGHT("light", LightController.class.getSimpleName());

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
}
