package com.skyline.skysmart.device.control;

import com.skyline.skysmart.device.enums.ControllerInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * [FEATURE INFO]<br/>
 * Device control center, offers device control api to web service.
 * All device controller should be registered here.
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

    public void dispatchControl(String instruction) {
        String[] strs = instruction.split(":");
        String controllerInstruction = strs[0];

        String subInstruction = instruction.substring(controllerInstruction.length() + 1);
        // judge which controller should be used
        if (ControllerInstruction.LIGHT.getInstruction().equals(controllerInstruction)) {
            LightController controller = (LightController) getController(ControllerInstruction.LIGHT.getClassName());
            controller.execute(subInstruction);
        }
    }

}
