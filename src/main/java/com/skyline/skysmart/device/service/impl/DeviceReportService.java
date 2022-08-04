package com.skyline.skysmart.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.message.IDeviceReportMessage;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.device.service.IDeviceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * [FEATURE INFO]<br/>
 * device report service
 *
 * @author Skyline
 * @create 2022-8-4 21:49
 * @since 1.0.0
 */
@Service
public class DeviceReportService implements IDeviceReportService {

    private RedisTemplate<String, Object> redisTemplate;
    private UserDeviceRelationDataConverter userDeviceRelationDataConverter;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserDeviceRelationDataConverter(UserDeviceRelationDataConverter userDeviceRelationDataConverter) {
        this.userDeviceRelationDataConverter = userDeviceRelationDataConverter;
    }

    /**
     * handle device report message
     *
     * @param deviceReportMessage IDeviceReportMessage
     */
    @Override
    public void handleReport(IDeviceReportMessage deviceReportMessage) {
        String K = RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix();
        String HK = RedisKeyPrefix.DEVICE_REGISTER_INFO_HASH_KEY.getKeyPrefix() + deviceReportMessage.getDeviceId();

        DeviceCachedInfo deviceCachedInfo = (DeviceCachedInfo) redisTemplate.opsForHash().get(K, HK);
        if (deviceCachedInfo == null) {
            Asserts.fail(ResultCode.NO_ELEMENT);
        }

        IUserDeviceRelationBO relationBO = userDeviceRelationDataConverter.castToUserDeviceRelationBO(deviceCachedInfo);

        deviceReportMessage.getDataMap().forEach(relationBO::setProperty);

        deviceCachedInfo = userDeviceRelationDataConverter.castToDeviceCachedInfo(relationBO);

        redisTemplate.opsForHash().put(K, HK, deviceCachedInfo);
    }
}
