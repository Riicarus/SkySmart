package com.skyline.skysmart.device.service.impl;

import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.device.service.IDeviceDBService;
import com.skyline.skysmart.device.service.IDeviceRegisterManager;
import com.skyline.skysmart.device.service.IUserDeviceRelationDBService;
import com.skyline.skysmart.device.entity.message.IDeviceRegisterMessage;
import com.skyline.skysmart.device.entity.message.IDeviceUnregisterMessage;
import com.skyline.skysmart.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * [FEATURE INFO]<br/>
 * device register manager
 *
 * @author Skyline
 * @create 2022/7/26 21:25
 * @since 1.0.0
 */
@Service
public class DeviceRegisterManager implements IDeviceRegisterManager {

    private IUserService userService;
    private IDeviceDBService deviceDBService;
    private IUserDeviceRelationDBService userDeviceRelationDBService;

    private UserDeviceRelationDataConverter userDeviceRelationDataConverter;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDeviceDBService(IDeviceDBService deviceDBService) {
        this.deviceDBService = deviceDBService;
    }

    @Autowired
    public void setUserDeviceRelationDBService(IUserDeviceRelationDBService userDeviceRelationDBService) {
        this.userDeviceRelationDBService = userDeviceRelationDBService;
    }

    @Autowired
    public void setUserDeviceRelationDataConverter(UserDeviceRelationDataConverter userDeviceRelationDataConverter) {
        this.userDeviceRelationDataConverter = userDeviceRelationDataConverter;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * register device <br/>
     * 1. get related device info, status, config and others <br/>
     * 2. cache device info to redis <br/>
     * 3. update device info in db <br/>
     *
     * @param deviceRegisterMessage IDeviceRegisterMessage
     */
    @Override
    public void register(IDeviceRegisterMessage deviceRegisterMessage) {
        DeviceCachedInfo deviceCachedInfo;
        IUserDeviceRelationBO relationBO;

        String K = RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix();
        String HK = RedisKeyPrefix.DEVICE_REGISTER_INFO_HASH_KEY.getKeyPrefix() + deviceRegisterMessage.getDeviceId();
        String deviceId = deviceRegisterMessage.getDeviceId();

        deviceCachedInfo = (DeviceCachedInfo) redisTemplate.opsForHash().get(K, HK);
        if (deviceCachedInfo != null) {
            relationBO = userDeviceRelationDataConverter.castToUserDeviceRelationBO(deviceCachedInfo);
        } else {
            relationBO = userDeviceRelationDBService.getRelationBOByDeviceId(deviceId);
        }

        deviceCachedInfo = userDeviceRelationDataConverter.castToDeviceCachedInfo(relationBO, deviceRegisterMessage);

        redisTemplate.opsForHash().put(K, HK, deviceCachedInfo);
        redisTemplate.expire(HK, 2, TimeUnit.MINUTES);

        deviceDBService.updateLastRegisterTime(deviceId, deviceRegisterMessage.getRegisterTime());
    }

    /**
     * unregister device <br/>
     * 1. remove device info from redis <br/>
     *
     * @param deviceUnregisterMessage IDeviceUnregisterMessage
     */
    @Override
    public void unregister(IDeviceUnregisterMessage deviceUnregisterMessage) {
        String deviceId = deviceUnregisterMessage.getDeviceId();

        String key = RedisKeyPrefix.DEVICE_REGISTER_INFO_HASH_KEY.getKeyPrefix() + deviceId;
        redisTemplate.opsForHash().delete(RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix(), key);
    }
}
