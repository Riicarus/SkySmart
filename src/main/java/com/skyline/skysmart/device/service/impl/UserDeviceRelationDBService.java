package com.skyline.skysmart.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.ApiException;
import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.converter.DeviceDataConverter;
import com.skyline.skysmart.device.entity.converter.ProductDataConverter;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.mapper.UserDeviceRelationMapper;
import com.skyline.skysmart.device.service.IDeviceDBService;
import com.skyline.skysmart.device.service.IUserDeviceRelationDBService;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;
import com.skyline.skysmart.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private UserDeviceRelationMapper userDeviceRelationMapper;

    private DeviceDataConverter deviceDataConverter;
    private ProductDataConverter productDataConverter;
    private UserDeviceRelationDataConverter userDeviceRelationDataConverter;

    @Autowired
    public void setDeviceDBService(IDeviceDBService deviceDBService) {
        this.deviceDBService = deviceDBService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
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
}
