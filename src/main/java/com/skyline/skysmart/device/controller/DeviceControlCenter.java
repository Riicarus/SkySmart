package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.core.util.HttpClientUtils;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.user.shiro.AssertPermission;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * [FEATURE INFO]<br/>
 * device controller center
 *
 * @author Skyline
 * @create 2022-8-10 17:20
 * @since 1.0.0
 */
@Api
@RestController
public class DeviceControlCenter {

    private RedisTemplate<String, Object> redisTemplate;
    private UserDeviceRelationDataConverter relationDataConverter;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setRelationDataConverter(UserDeviceRelationDataConverter relationDataConverter) {
        this.relationDataConverter = relationDataConverter;
    }

    @GetMapping("/user/{uid}/device/{deviceId}/turnoff")
    public ResponseResult<Boolean> turnoff(
            @PathVariable("uid") String uid,
            @PathVariable("deviceId") String deviceId
    ) {
        AssertPermission.single("device:*:" + uid);

        String K = RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix();
        String HK = RedisKeyPrefix.DEVICE_REGISTER_INFO_HASH_KEY.getKeyPrefix() + deviceId;

        DeviceCachedInfo deviceCachedInfo = (DeviceCachedInfo) redisTemplate.opsForHash().get(K, HK);
        IUserDeviceRelationBO relationBO = relationDataConverter.castToUserDeviceRelationBO(deviceCachedInfo);

        String gatewayDeviceId = relationBO.getDeviceBO().getGatewayDeviceId();
        HK = RedisKeyPrefix.DEVICE_REGISTER_INFO_HASH_KEY.getKeyPrefix() + gatewayDeviceId;
        DeviceCachedInfo gatewayDeviceCachedInfo = (DeviceCachedInfo) redisTemplate.opsForHash().get(K, HK);
        IUserDeviceRelationBO gatewayRelationBO = relationDataConverter.castToUserDeviceRelationBO(deviceCachedInfo);

        String gatewayIp = gatewayRelationBO.getDeviceBO().getIp();

        String turnoffUrl = "http://" + gatewayIp + "/device/" + deviceId + "/turnoff";

        String res = HttpClientUtils.doGet(turnoffUrl, null);

        if (res.equals("1")) {
            return ResponseResult.success(true);
        }

        return ResponseResult.failed();
    }

}
