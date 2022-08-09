package com.skyline.skysmart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.device.entity.enums.PropertyType;
import com.skyline.skysmart.device.entity.message.impl.DeviceReportMessage;
import com.skyline.skysmart.device.entity.model.CommonProperty;
import com.skyline.skysmart.device.entity.model.CommonValueType;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.device.entity.model.IValueType;
import com.skyline.skysmart.device.service.IUserDeviceRelationDBService;
import com.skyline.skysmart.user.service.interfaces.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class SkySmartApplicationTests {

    private IUserService userService;
    private IUserDeviceRelationDBService relationDBService;

    @Autowired
    public void setRelationDBService(IUserDeviceRelationDBService relationDBService) {
        this.relationDBService = relationDBService;
    }

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
        Md5Hash md5Hash = new Md5Hash("skyline", "1655029107440", 1024);
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

    @Test
    void generateUuid() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }

    @Test
    void generateDefaultProperties() {

/*        CommonValueType temperatureValueType = new CommonValueType("int", "75", "-15", "`C", new HashMap<>());
        CommonValueType humidityValueType = new CommonValueType("int", "100", "0", "`%", new HashMap<>());
        CommonValueType lightValueType = new CommonValueType("int", "1", "0", "", new HashMap<>());
        IProperty temperatureProperty = new CommonProperty("temperature", "temperature", PropertyType.DEVICE_ONLY, temperatureValueType, "0", new HashMap<>());
        IProperty humidityProperty = new CommonProperty("humidity", "humidity", PropertyType.DEVICE_ONLY, humidityValueType, "0", new HashMap<>());
        IProperty lightProperty = new CommonProperty("light", "whether it is light enough", PropertyType.DEVICE_ONLY, lightValueType, "0", new HashMap<>());

        HashMap<String, IProperty> defaultProperties = new HashMap<>();
        defaultProperties.put(temperatureProperty.getId(), temperatureProperty);
        defaultProperties.put(humidityProperty.getId(), humidityProperty);
        defaultProperties.put(lightProperty.getId(), lightProperty);*/

        CommonValueType aliveDeviceNumberValue = new CommonValueType("int", "20", "0", "", new HashMap<>());
        IProperty aliveDeviceNumberProperty = new CommonProperty("alive-device-number", "number of alive device registered to one gateway",
                PropertyType.DEVICE_ONLY, aliveDeviceNumberValue, "0", new HashMap<>());
        HashMap<String, IProperty> defaultProperties = new HashMap<>();
        defaultProperties.put(aliveDeviceNumberProperty.getId(), aliveDeviceNumberProperty);

        System.out.println(JSONObject.toJSONString(defaultProperties));
    }

    @Test
    void testGenerateJsonHashMap() {
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("light", "1");
        dataMap.put("temperature", "26");
        dataMap.put("humidity", "50");

        DeviceReportMessage deviceReportMessage = new DeviceReportMessage();
        deviceReportMessage.setDeviceId("26dd970d-c066-45f1-beb3-e0df5ebf0c91");
        deviceReportMessage.setDataMap(dataMap);

        System.out.println(JSONObject.toJSONString(deviceReportMessage));

        JSONObject.parseObject(JSONObject.toJSONString(deviceReportMessage), new TypeReference<DeviceReportMessage>(){});
    }
}
