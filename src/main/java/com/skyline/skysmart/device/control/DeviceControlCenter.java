package com.skyline.skysmart.device.control;

import com.skyline.skysmart.device.control.controller.DeviceController;
import com.skyline.skysmart.device.enums.ControllerInstruction;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * [FEATURE INFO]<br/>
 * Device control center, offers device control api to web service.<br/>
 * All device controller should be registered here.<br/>
 * Use strategy pattern<br/>
 *
 * @author Skyline
 * @create 2022/6/10 17:54
 * @since 1.0.0
 */
@Component
public class DeviceControlCenter {

    @Autowired
    private Map<String, DeviceController> controllers;

    public Map<String, DeviceController> getControllers() {
        return this.controllers;
    }

    public DeviceController getController(String className) {
        return this.controllers.get(className);
    }

    /**
     * determine witch controller to execute the instruction
     *
     * @param instruction String
     */
    public void dispatchControl(String instruction) {
        String[] strs = instruction.split(InstructionUtils.PARAM_SEPARATOR);
        String controllerInstruction = strs[0];

        String subInstruction = instruction.substring(controllerInstruction.length() + 1);
        // judge which controller should be used
        DeviceController controller = getController(ControllerInstruction.SCREEN.getClassName(controllerInstruction));

        // execute instruction
        controller.execute(subInstruction);
    }

}
