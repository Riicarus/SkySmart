package com.skyline.skysmart;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.auth.service.interfaces.IUserService;
import com.skyline.skysmart.device.control.DeviceControlCenter;
import com.skyline.skysmart.device.data.dto.DeviceInternetInfo;
import com.skyline.skysmart.device.data.dto.InstructionUnit;
import com.skyline.skysmart.device.service.interfaces.ISceneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class SkySmartApplicationTests {

    private IUserService userService;
    private ISceneService sceneService;
    private DeviceControlCenter deviceControlCenter;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSceneService(ISceneService sceneService) {
        this.sceneService = sceneService;
    }

    @Autowired
    public void setDeviceControlCenter(DeviceControlCenter deviceControlCenter) {
        this.deviceControlCenter = deviceControlCenter;
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
    void testDeviceControlCenter() {
        DeviceInternetInfo info = new DeviceInternetInfo("123456789", "136.20.116.49", "9010", "skengj4634624xkfalg");
        deviceControlCenter.register(info);
        DeviceInternetInfo info_1 = deviceControlCenter.getDeviceInternetInfo("123456789");
        System.out.println(info);
        System.out.println(info_1.toString());
    }

    @Test
    void testQueueJson() {
        Queue<InstructionUnit> queue = new LinkedList<>();
        List<String> param_1 = new ArrayList<>();
        param_1.add("Wednesday");
        param_1.add("12345");
        queue.add(new InstructionUnit("on", param_1));
        List<String> param_2 = new ArrayList<>();
        param_2.add("Friday");
        param_2.add("123");
        queue.add(new InstructionUnit("off", param_2));
        System.out.println(JSONObject.toJSONString(queue));
    }

    @Test
    void testSceneDAOJson() {
        HashMap<String, Queue<InstructionUnit>> rawDeviceInstructionMap = new HashMap<>();

        Queue<InstructionUnit> queue_1 = new LinkedList<>();
        List<String> param_1 = new ArrayList<>();
        param_1.add("Wednesday");
        param_1.add("12345");
        queue_1.add(new InstructionUnit("on", param_1));
        List<String> param_2 = new ArrayList<>();
        param_2.add("Friday");
        param_2.add("123");
        queue_1.add(new InstructionUnit("off", param_2));

        Queue<InstructionUnit> queue_2 = new LinkedList<>();
        List<String> param_3 = new ArrayList<>();
        param_3.add("Wednesday");
        param_3.add("12345");
        queue_2.add(new InstructionUnit("on", param_3));
        List<String> param_4 = new ArrayList<>();
        param_4.add("Friday");
        param_4.add("123");
        queue_2.add(new InstructionUnit("off", param_4));

        rawDeviceInstructionMap.put("light_1", queue_1);
        rawDeviceInstructionMap.put("light_2", queue_2);

        //System.out.println(JSONObject.toJSONString(rawDeviceInstructionMap));

        HashMap<String, Queue<InstructionUnit>> rawDeviceInstructionMap_1 = JSONObject.parseObject(JSONObject.toJSONString(rawDeviceInstructionMap), new TypeReference<HashMap<String, Queue<InstructionUnit>>>(){});
        System.out.println(JSONObject.toJSONString(rawDeviceInstructionMap_1));
    }

    @Test
    void testGetCacheSceneBO() {
        // System.out.println(sceneService.getCacheScene().get(0).getInstructionQueue());
    }

}
