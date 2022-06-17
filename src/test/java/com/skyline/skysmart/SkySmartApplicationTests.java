package com.skyline.skysmart;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.auth.service.interfaces.IUserService;
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


}
