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
        String deviceId = deviceRegisterMessage.getDeviceId();
        IUserDeviceRelationBO userDeviceRelationBO = userDeviceRelationDBService.getRelationBOByDeviceId(deviceId);

        String key = RedisKeyPrefix.DEVICE_REGISTER_INFO_HASH_KEY.getKeyPrefix() + userDeviceRelationBO.getDeviceId();
        DeviceCachedInfo deviceCachedInfo = userDeviceRelationDataConverter.castToDeviceCachedInfo(userDeviceRelationBO, deviceRegisterMessage);
        redisTemplate.opsForHash().put(RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix(), key, deviceCachedInfo);

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
