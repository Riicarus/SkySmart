package com.skyline.skysmart.device.control;

import com.alibaba.fastjson.JSONObject;
import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.dto.DeviceControlRequest;
import com.skyline.skysmart.device.data.dto.DeviceInternetInfo;
import com.skyline.skysmart.device.util.InstructionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * DeviceControlCenter
 *
 * @author Skyline
 * @create 2022/6/19 11:39
 * @since 1.0.0
 */
@Component
public class DeviceControlCenter {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * save device's internet info to Redis
     *
     * @param info DeviceInternetInfo
     */
    public void  register(DeviceInternetInfo info) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        String key = RedisKeyPrefix.DEVICE_NET_INFO.getKeyPrefix() + info.getDeviceId();
        hashOperations.put(key, "IP", info.getIP());
        hashOperations.put(key, "Port", info.getPort());
        hashOperations.put(key, "MAC", info.getMAC());
    }

    /**
     * get DeviceInternetInfo from Redis
     *
     * @param deviceId String
     * @return DeviceInternetInfo
     */
    public DeviceInternetInfo getDeviceInternetInfo(String deviceId) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        String key = RedisKeyPrefix.DEVICE_NET_INFO.getKeyPrefix() + deviceId;
        HashMap<String, String> infoParams = (HashMap<String, String>) hashOperations.entries(key);

        if (infoParams.isEmpty()) {
            Asserts.fail(ResultCode.NO_ELEMENT);
        }

        return new DeviceInternetInfo(deviceId, infoParams.get("IP"),
                infoParams.get("Port"), infoParams.get("MAC"));
    }

    /**
     * generate instruction to DeviceControlRequest
     *
     * @param instruction String
     * @return DeviceControlRequest
     */
    private DeviceControlRequest generateControlRequest(String instruction) {
        String deviceId = InstructionUtils.getDeviceId(instruction);
        DeviceInternetInfo info = getDeviceInternetInfo(deviceId);

        String url = "http://" + info.getIP() + ":" + info.getPort();
        HashMap<String, String> params = new HashMap<>();
        params.put("instruction", instruction);

        return new DeviceControlRequest(url, params);
    }

    /**
     * push DeviceControlRequest into MessageQueue(Kafka)
     *
     * @param deviceControlRequest DeviceControlRequest
     */
    private void sendRequest(DeviceControlRequest deviceControlRequest) {
        // todo push request into Kafka
        System.out.println(JSONObject.toJSONString(deviceControlRequest));
    }

    /**
     * dispatch instruction to device
     *
     * @param instruction String
     */
    public void doDispatch(String instruction) {
        DeviceControlRequest request = generateControlRequest(instruction);
        sendRequest(request);
    }
}
