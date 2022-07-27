package com.skyline.skysmart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.device.entity.model.CommonProperty;
import com.skyline.skysmart.device.entity.model.CommonValueType;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.device.entity.model.IValueType;
import com.skyline.skysmart.user.service.interfaces.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class SkySmartApplicationTests {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
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
    void testMd5Hash() {
        Md5Hash md5Hash = new Md5Hash("192.168.1.1", "00:0A:02:0B:03:0C", 1024);
        System.out.println(md5Hash);
    }

    @Test
    void testProperty() {
        IProperty property = new CommonProperty();
        property.setId("cpu_usage");
        property.setName("cpu now time usage");
        IValueType valueType = new CommonValueType();
        valueType.setType("double");
        valueType.setMaxValue("100");
        valueType.setMinValue("0");
        valueType.setUnit("percent");
        property.setValueType(valueType);

        System.out.println(JSON.toJSONString(property));
    }

}
