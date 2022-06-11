package com.skyline.skysmart;

import com.skyline.skysmart.device.control.DeviceControlCenter;
import com.skyline.skysmart.device.control.controller.DeviceController;
import com.skyline.skysmart.device.control.controller.LightController;
import com.skyline.skysmart.device.control.controller.ScreenController;
import com.skyline.skysmart.device.enums.ControllerInstruction;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SkySmartApplicationTests {

    @Autowired
    private DeviceControlCenter controlCenter;

    @Test
    void testRegisterController() {
        DeviceController lightController = controlCenter.getController(LightController.class.getSimpleName());
        DeviceController screenController = controlCenter.getController(ScreenController.class.getSimpleName());
        System.out.println(lightController);
        System.out.println(screenController);
    }

    @Test
    void testDispatchDeviceController() {
        controlCenter.dispatchControl("light&deviceId=2345&color=ffffff&id=2345&off=30000");
    }

    @Test
    void testInstructionGenerator() {
        HashMap<String, String> params = new HashMap<>();
        params.put("color", "ffffff");
        params.put("off", "30000");
        params.put("deviceId", "2345");

        String instruction = InstructionUtils.generate(ControllerInstruction.LIGHT, params);
        System.out.println(instruction);
    }

    @Test
    void testInstructionParse() {
        HashMap<String, String> params = InstructionUtils.parse("color=ffffff&id=2345&off=30000");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            System.out.println(entry);
        }
    }

}
