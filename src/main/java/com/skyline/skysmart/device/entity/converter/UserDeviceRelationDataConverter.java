package com.skyline.skysmart.device.entity.converter;

import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.bo.impl.UserDeviceRelationBO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.dto.DeviceUserInfoDTO;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.device.entity.message.IDeviceRegisterMessage;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;
import org.springframework.stereotype.Component;

/**
 * [FEATURE INFO]<br/>
 * user device relation data converter
 *
 * @author Skyline
 * @create 2022/7/27 9:23
 * @since 1.0.0
 */
@Component
public class UserDeviceRelationDataConverter {

    public IUserDeviceRelationBO castToUserDeviceRelationBO(UserDeviceRelationDAO userDeviceRelationDAO, IDeviceBO deviceBO, IUserBO userBO) {
        if (userBO == null || userDeviceRelationDAO == null || deviceBO == null) {
            return new UserDeviceRelationBO();
        }

        IUserDeviceRelationBO userDeviceRelationBO = new UserDeviceRelationBO();
        userDeviceRelationBO.mapUserDeviceRelationDAO(userDeviceRelationDAO);
        userDeviceRelationBO.mapDeviceBO(deviceBO);
        userDeviceRelationBO.mapUserBO(userBO);

        return userDeviceRelationBO;
    }

    public DeviceCachedInfo castToDeviceCachedInfo(IUserDeviceRelationBO userDeviceRelationBO, IDeviceRegisterMessage deviceRegisterMessage) {
        DeviceCachedInfo deviceCachedInfo = new DeviceCachedInfo();
        deviceCachedInfo.setRelationId(userDeviceRelationBO.getId());
        deviceCachedInfo.setAliasName(userDeviceRelationBO.getAliasName());
        deviceCachedInfo.setDeviceId(userDeviceRelationBO.getDeviceId());
        deviceCachedInfo.setDeviceName(userDeviceRelationBO.getDeviceBO().getName());
        deviceCachedInfo.setUid(userDeviceRelationBO.getUserBO().getUid());
        deviceCachedInfo.setUsername(userDeviceRelationBO.getUserBO().getUsername());
        deviceCachedInfo.setProductId(userDeviceRelationBO.getDeviceBO().getProductId());
        deviceCachedInfo.setProductName(userDeviceRelationBO.getDeviceBO().getProductBO().getName());
        deviceCachedInfo.setProductType(userDeviceRelationBO.getDeviceBO().getProductBO().getType());
        deviceCachedInfo.setIp(deviceRegisterMessage.getIp());
        deviceCachedInfo.setMac(deviceRegisterMessage.getMac());
        deviceCachedInfo.setCreateTime(userDeviceRelationBO.getDeviceBO().getCreateTime());
        deviceCachedInfo.setLastRegisterTime(deviceRegisterMessage.getRegisterTime());
        deviceCachedInfo.setPresets(userDeviceRelationBO.getPresets());
        deviceCachedInfo.setCurrentPresetName(userDeviceRelationBO.getCurrentPresetName());

        return deviceCachedInfo;
    }

    public DeviceUserInfoDTO castToDeviceUserInfoDTO(IUserDeviceRelationBO userDeviceRelationBO) {
        DeviceUserInfoDTO deviceUserInfoDTO = new DeviceUserInfoDTO();
        deviceUserInfoDTO.setUid(userDeviceRelationBO.getUid());
        deviceUserInfoDTO.setDeviceId(userDeviceRelationBO.getDeviceId());
        deviceUserInfoDTO.setProductId(userDeviceRelationBO.getDeviceBO().getProductId());
        deviceUserInfoDTO.setAliasName(userDeviceRelationBO.getAliasName());
        deviceUserInfoDTO.setCurrentPresetName(userDeviceRelationBO.getCurrentPresetName());

        return deviceUserInfoDTO;
    }

}
