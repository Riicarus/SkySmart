package com.skyline.skysmart.device.service.impl;

import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.enums.PropertyType;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.device.service.IDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * [FEATURE INFO]<br/>
 * device data service
 *
 * @author Skyline
 * @create 2022-8-9 18:30
 * @since 1.0.0
 */
@Service
public class DeviceDataService implements IDeviceDataService {

    private UserDeviceRelationDataConverter relationDataConverter;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRelationDataConverter(UserDeviceRelationDataConverter relationDataConverter) {
        this.relationDataConverter = relationDataConverter;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * list all sensor's data
     *
     * @param gatewayId String, deviceId
     * @return HashMap, key: deviceId + propertyId, value: propertyValue
     */
    @Override
    public HashMap<String, String> listAllSensorData(String gatewayId) {
        HashMap<String, String> sensorDataMap = new HashMap<>();

        String K = RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix();

        List<Object> deviceCachedInfoList = redisTemplate.opsForHash().values(K);
        ArrayList<IUserDeviceRelationBO> relationBOList = new ArrayList<>();

        for (Object o : deviceCachedInfoList) {
            String gatewayDeviceId = ((DeviceCachedInfo) o).getGatewayDeviceId();
            if (gatewayId.equals(gatewayDeviceId)) {
                relationBOList.add(relationDataConverter.castToUserDeviceRelationBO((DeviceCachedInfo) o));
            }
        }

        for (IUserDeviceRelationBO relationBO : relationBOList) {
            relationBO.getProperties().forEach((k ,v) -> {
                if (v.getPropertyType().equals(PropertyType.SENSOR)) {
                    sensorDataMap.put(k, v.getValue());
                }
            });
        }

        return sensorDataMap;
    }
}
