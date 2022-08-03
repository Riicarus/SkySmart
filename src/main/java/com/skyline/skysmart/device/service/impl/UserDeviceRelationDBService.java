package com.skyline.skysmart.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.ApiException;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.converter.DeviceDataConverter;
import com.skyline.skysmart.device.entity.converter.ProductDataConverter;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.dto.UserDeviceDetailInfoDTO;
import com.skyline.skysmart.device.entity.dto.UserDeviceInfoDTO;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.device.mapper.UserDeviceRelationMapper;
import com.skyline.skysmart.device.service.IDeviceDBService;
import com.skyline.skysmart.device.service.IUserDeviceRelationDBService;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;
import com.skyline.skysmart.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * [FEATURE INFO]<br/>
 * user device relation db service
 *
 * @author Skyline
 * @create 2022/7/27 9:28
 * @since 1.0.0
 */
@Service
public class UserDeviceRelationDBService implements IUserDeviceRelationDBService {

    private IDeviceDBService deviceDBService;
    private IUserService userService;
    private IUserDeviceRelationDBService userDeviceRelationDBService;

    private UserDeviceRelationMapper userDeviceRelationMapper;

    private DeviceDataConverter deviceDataConverter;
    private ProductDataConverter productDataConverter;
    private UserDeviceRelationDataConverter userDeviceRelationDataConverter;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setDeviceDBService(IDeviceDBService deviceDBService) {
        this.deviceDBService = deviceDBService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDeviceRelationDBService(IUserDeviceRelationDBService userDeviceRelationDBService) {
        this.userDeviceRelationDBService = userDeviceRelationDBService;
    }

    @Autowired
    public void setUserDeviceRelationMapper(UserDeviceRelationMapper userDeviceRelationMapper) {
        this.userDeviceRelationMapper = userDeviceRelationMapper;
    }

    @Autowired
    public void setDeviceDataConverter(DeviceDataConverter deviceDataConverter) {
        this.deviceDataConverter = deviceDataConverter;
    }

    @Autowired
    public void setProductDataConverter(ProductDataConverter productDataConverter) {
        this.productDataConverter = productDataConverter;
    }

    @Autowired
    public void setUserDeviceRelationDataConverter(UserDeviceRelationDataConverter userDeviceRelationDataConverter) {
        this.userDeviceRelationDataConverter = userDeviceRelationDataConverter;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public IUserDeviceRelationBO getRelationBO(String relationId) {
        UserDeviceRelationDAO userDeviceRelationDAO = getRelationDAO(relationId);

        String deviceId = userDeviceRelationDAO.getDeviceId();
        IDeviceBO deviceBO = deviceDBService.getDeviceBO(deviceId);

        String uid = userDeviceRelationDAO.getUid();
        IUserBO userBO = userService.getUserBOByUid(uid);

        return userDeviceRelationDataConverter.castToUserDeviceRelationBO(userDeviceRelationDAO, deviceBO, userBO);
    }

    @Override
    public UserDeviceRelationDAO getRelationDAO(String relationId) {
        return userDeviceRelationMapper.selectById(relationId);
    }

    @Override
    public IUserDeviceRelationBO getRelationBOByDeviceId(String deviceId) {
        QueryWrapper<UserDeviceRelationDAO> relationWrapper = new QueryWrapper<>();
        relationWrapper.eq("device_id", deviceId);
        UserDeviceRelationDAO relationDAO = userDeviceRelationMapper.selectOne(relationWrapper);

        if (relationDAO == null) {
            throw new ApiException(ResultCode.NO_ELEMENT);
        }

        return getRelationBO(relationDAO.getId());
    }

    /**
     * list all user's device
     *
     * @param uid String, uid
     * @return ArrayList of UserDeviceInfoDTO
     */
    @Override
    public ArrayList<UserDeviceInfoDTO> listDeviceUserInfoDTO(String uid) {
        ArrayList<UserDeviceInfoDTO> infoList = new ArrayList<>();

        QueryWrapper<UserDeviceRelationDAO> relationWrapper = new QueryWrapper<>();
        relationWrapper.eq("uid", uid);

        List<UserDeviceRelationDAO> userDeviceRelationDAOList = userDeviceRelationMapper.selectList(relationWrapper);
        if (userDeviceRelationDAOList.isEmpty()) {
            Asserts.fail(ResultCode.NO_ELEMENT);
        }

        ArrayList<IUserDeviceRelationBO> userDeviceRelationBOList = new ArrayList<>();
        userDeviceRelationDAOList.forEach(relationDAO -> userDeviceRelationBOList.add(getRelationBO(relationDAO.getId())));

        userDeviceRelationBOList.forEach(relationBO -> infoList.add(userDeviceRelationDataConverter.castToDeviceUserInfoDTO(relationBO)));

        return infoList;
    }

    /**
     * list all user's online device
     *
     * @param uid String, uid
     * @return ArrayList of DeviceUserInfoSTO
     */
    @Override
    public ArrayList<UserDeviceInfoDTO> listOnlineDeviceUserInfoDTO(String uid) {
        ArrayList<UserDeviceInfoDTO> infoList = new ArrayList<>();

        List<Object> deviceCachedInfoList = redisTemplate.opsForHash().values(RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix());
        for (Object deviceCachedInfo : deviceCachedInfoList) {
            if (uid.equals(((DeviceCachedInfo) deviceCachedInfo).getUid())) {
                infoList.add(userDeviceRelationDataConverter.castToDeviceUserInfoDTO((DeviceCachedInfo) deviceCachedInfo));
            }
        }

        return infoList;
    }

    /**
     * get device user detail info dto
     *
     * @param deviceId String, deviceId
     * @return UserDeviceDetailInfoDTO
     */
    @Override
    public UserDeviceDetailInfoDTO getDeviceUserDetailInfoDTO(String deviceId) {
        UserDeviceDetailInfoDTO userDeviceDetailInfoDTO;

        String K = RedisKeyPrefix.DEVICE_REGISTER_INFO_KEY.getKeyPrefix();
        String HK = RedisKeyPrefix.DEVICE_REGISTER_INFO_HASH_KEY.getKeyPrefix() + deviceId;

        DeviceCachedInfo deviceCachedInfo = (DeviceCachedInfo) redisTemplate.opsForHash().get(K, HK);
        if (deviceCachedInfo != null) {
            userDeviceDetailInfoDTO = userDeviceRelationDataConverter.castToDeviceUserDetailInfoDTO(deviceCachedInfo, true);
        } else {
            IUserDeviceRelationBO userDeviceRelationBO = userDeviceRelationDBService.getRelationBOByDeviceId(deviceId);
            userDeviceDetailInfoDTO = userDeviceRelationDataConverter.castToDeviceUserDetailInfoDTO(userDeviceRelationBO, false);
        }

        return userDeviceDetailInfoDTO;
    }
}
