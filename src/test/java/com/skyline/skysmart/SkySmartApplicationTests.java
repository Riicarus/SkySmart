package com.skyline.skysmart;

import com.skyline.skysmart.device.control.DeviceControlCenter;
import com.skyline.skysmart.device.control.LightController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkySmartApplicationTests {

    @Autowired
    private DeviceControlCenter controlCenter;

    @Test
    void testRegisterController() {
        LightController controller = (LightController) controlCenter.getController(LightController.class.getSimpleName());
        System.out.println(controller);
        controlCenter.dispatchControl("light:on:2000");
    }

}
