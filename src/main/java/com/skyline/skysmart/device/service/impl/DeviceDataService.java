package com.skyline.skysmart.device.service.impl;

import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.enums.PropertyType;
import com.skyline.skysmart.device.entity.model.IProperty;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.device.service.IDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @return HashMap, key_outer: deviceId, key_inner: propertyId, value: propertyValue
     */
    @Override
    public HashMap<String, HashMap<String, String>> listAllSensorData(String gatewayId) {
        HashMap<String, HashMap<String, String>> sensorDataMap = new HashMap<>();

        String K = RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix();

        List<Object> deviceCachedInfoList = redisTemplate.opsForHash().values(K);
        ArrayList<IUserDeviceRelationBO> relationBOList = new ArrayList<>();

        for (Object o : deviceCachedInfoList) {
            String gatewayDeviceId = ((DeviceCachedInfo) o).getGatewayDeviceId();
            if (gatewayId.equals(gatewayDeviceId)) {
                relationBOList.add(relationDataConverter.castToUserDeviceRelationBO((DeviceCachedInfo) o));
            }
        }

        relationBOList.forEach(bo -> sensorDataMap.put(bo.getDeviceId(), new HashMap<>()));

        for (IUserDeviceRelationBO relationBO : relationBOList) {
            relationBO.getProperties().forEach((k ,v) -> {
                if (v.getPropertyType().equals(PropertyType.SENSOR)) {
                    sensorDataMap.get(relationBO.getDeviceId()).put(k, v.getValue());
                }
            });
        }

        for (Map.Entry<String, HashMap<String, String>> entry : sensorDataMap.entrySet()) {
            if (entry.getValue().isEmpty()) {
                sensorDataMap.remove(entry.getKey());
            }
        }

        return sensorDataMap;
    }

    /**
     * list all sensors' info, like: deviceId-aliasName-propertyIds
     *
     * @param gatewayId String, deviceId of gateway
     * @return ArrayList
     */
    @Override
    public ArrayList<String> listAllSensorInfo(String gatewayId) {
        ArrayList<String> sensorInfoList = new ArrayList<>();

        String K = RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix();

        List<Object> deviceCachedInfoList = redisTemplate.opsForHash().values(K);
        ArrayList<IUserDeviceRelationBO> relationBOList = new ArrayList<>();

        for (Object o : deviceCachedInfoList) {
            String gatewayDeviceId = ((DeviceCachedInfo) o).getGatewayDeviceId();
            if (gatewayId.equals(gatewayDeviceId)) {
                IUserDeviceRelationBO relationBO = relationDataConverter.castToUserDeviceRelationBO((DeviceCachedInfo) o);
                HashMap<String, IProperty> properties = relationBO.getProperties();

                for (Map.Entry<String, IProperty> entry : properties.entrySet()) {
                    if (entry.getValue().getPropertyType().equals(PropertyType.SENSOR)) {
                        relationBOList.add(relationBO);
                        break;
                    }
                }
            }
        }

        relationBOList.forEach(bo -> {
            StringBuilder sb = new StringBuilder();
            sb.append(bo.getDeviceId()).append("*").append(bo.getAliasName()).append("*");
            bo.getProperties().forEach((k, v) -> {
                if (v.getPropertyType().equals(PropertyType.SENSOR)) {
                    sb.append(v.getId()).append("/");
                }
            });

            String str = sb.toString();
            sensorInfoList.add(str.substring(0, str.length() - 1));
        });

        return sensorInfoList;
    }
}
