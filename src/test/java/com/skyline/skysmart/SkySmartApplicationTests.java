package com.skyline.skysmart;

import com.skyline.skysmart.device.enums.ControllerInstruction;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SkySmartApplicationTests {

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
