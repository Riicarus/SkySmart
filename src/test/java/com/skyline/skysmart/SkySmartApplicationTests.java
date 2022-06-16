package com.skyline.skysmart;

import com.skyline.skysmart.auth.service.interfaces.IUserService;
import com.skyline.skysmart.device.enums.ControllerInstruction;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SkySmartApplicationTests {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
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

    @Test
    void testChangeUsername() {
        userService.changeUsername("26dd970d-c066-45f1-beb3-e0df5ebf0c91", "skyline");
    }

}
