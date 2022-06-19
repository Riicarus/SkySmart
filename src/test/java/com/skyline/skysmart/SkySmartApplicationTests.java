package com.skyline.skysmart;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.auth.service.interfaces.IUserService;
import com.skyline.skysmart.device.data.control.DeviceControlCenter;
import com.skyline.skysmart.device.data.dto.DeviceInternetInfo;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SkySmartApplicationTests {

    private IUserService userService;
    private DeviceControlCenter deviceControlCenter;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDeviceControlCenter(DeviceControlCenter deviceControlCenter) {
        this.deviceControlCenter = deviceControlCenter;
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

    @Test
    void testMapProperties() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "room1_light");
        hashMap.put("type", "light");
        hashMap.put("color", "sun light");
        String json = new org.json.JSONObject(hashMap).toString();
        System.out.println(json);

        HashMap<String, String> map = JSONObject.parseObject(json, new TypeReference<HashMap<String, String>>(){});
        System.out.println(map.toString());
    }

    @Test
    void testInstruction() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "room1_light");
        hashMap.put("type", "light");
        hashMap.put("color", "sun-light");
        String instruction = InstructionUtils.generate("202039485430", hashMap);
        String subInstruction = InstructionUtils.getSubInstruction(instruction);
        String deviceId = InstructionUtils.getDeviceId(instruction);
        System.out.println(instruction);
        System.out.println(subInstruction);
        System.out.println(deviceId);
    }

    @Test
    void testDeviceControlCenter() {
        DeviceInternetInfo info = new DeviceInternetInfo("123456789", "136.20.116.49", "9010", "skengj4634624xkfalg");
        deviceControlCenter.register(info);
        DeviceInternetInfo info_1 = deviceControlCenter.getDeviceInternetInfo("123456789");
        System.out.println(info);
        System.out.println(info_1.toString());
    }
}
