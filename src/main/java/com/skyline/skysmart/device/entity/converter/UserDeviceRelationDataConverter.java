package com.skyline.skysmart.device.entity.converter;

import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.bo.impl.UserDeviceRelationBO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.dto.UserDeviceDetailInfoDTO;
import com.skyline.skysmart.device.entity.dto.UserDeviceInfoDTO;
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
        userDeviceRelationBO.syncProperty();

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
        deviceCachedInfo.setProperties(userDeviceRelationBO.getProperties());

        return deviceCachedInfo;
    }

    public UserDeviceInfoDTO castToDeviceUserInfoDTO(IUserDeviceRelationBO userDeviceRelationBO) {
        UserDeviceInfoDTO userDeviceInfoDTO = new UserDeviceInfoDTO();
        userDeviceInfoDTO.setUid(userDeviceRelationBO.getUid());
        userDeviceInfoDTO.setDeviceId(userDeviceRelationBO.getDeviceId());
        userDeviceInfoDTO.setProductId(userDeviceRelationBO.getDeviceBO().getProductId());
        userDeviceInfoDTO.setAliasName(userDeviceRelationBO.getAliasName());
        userDeviceInfoDTO.setCurrentPresetName(userDeviceRelationBO.getCurrentPresetName());

        return userDeviceInfoDTO;
    }

    public UserDeviceInfoDTO castToDeviceUserInfoDTO(DeviceCachedInfo deviceCachedInfo) {
        UserDeviceInfoDTO userDeviceInfoDTO = new UserDeviceInfoDTO();
        userDeviceInfoDTO.setUid(deviceCachedInfo.getUid());
        userDeviceInfoDTO.setDeviceId(deviceCachedInfo.getDeviceId());
        userDeviceInfoDTO.setProductId(deviceCachedInfo.getProductId());
        userDeviceInfoDTO.setAliasName(deviceCachedInfo.getAliasName());
        userDeviceInfoDTO.setCurrentPresetName(deviceCachedInfo.getCurrentPresetName());

        return userDeviceInfoDTO;
    }

    public UserDeviceDetailInfoDTO castToDeviceUserDetailInfoDTO(IUserDeviceRelationBO userDeviceRelationBO, Boolean networkStatus) {
        UserDeviceDetailInfoDTO userDeviceDetailInfoDTO = new UserDeviceDetailInfoDTO();
        userDeviceDetailInfoDTO.setUid(userDeviceRelationBO.getUid());
        userDeviceDetailInfoDTO.setDeviceId(userDeviceRelationBO.getDeviceId());
        userDeviceDetailInfoDTO.setDeviceName(userDeviceRelationBO.getDeviceBO().getName());
        userDeviceDetailInfoDTO.setAliasName(userDeviceRelationBO.getAliasName());
        userDeviceDetailInfoDTO.setProductId(userDeviceRelationBO.getDeviceBO().getProductId());
        userDeviceDetailInfoDTO.setProductName(userDeviceRelationBO.getDeviceBO().getProductBO().getName());
        userDeviceDetailInfoDTO.setProductType(userDeviceRelationBO.getDeviceBO().getProductBO().getType());
        userDeviceDetailInfoDTO.setIp("offline");
        userDeviceDetailInfoDTO.setMac("offline");
        userDeviceDetailInfoDTO.setCurrentPresetName(userDeviceRelationBO.getCurrentPresetName());
        userDeviceDetailInfoDTO.setPresets(userDeviceRelationBO.getPresets());
        userDeviceDetailInfoDTO.setNetworkStatus(networkStatus);
        userDeviceDetailInfoDTO.setProperties(userDeviceRelationBO.getProperties());

        return userDeviceDetailInfoDTO;
    }

    public UserDeviceDetailInfoDTO castToDeviceUserDetailInfoDTO(DeviceCachedInfo deviceCachedInfo, Boolean networkStatus) {
        UserDeviceDetailInfoDTO userDeviceDetailInfoDTO = new UserDeviceDetailInfoDTO();
        userDeviceDetailInfoDTO.setUid(deviceCachedInfo.getUid());
        userDeviceDetailInfoDTO.setDeviceId(deviceCachedInfo.getDeviceId());
        userDeviceDetailInfoDTO.setDeviceName(deviceCachedInfo.getDeviceName());
        userDeviceDetailInfoDTO.setAliasName(deviceCachedInfo.getAliasName());
        userDeviceDetailInfoDTO.setProductId(deviceCachedInfo.getProductId());
        userDeviceDetailInfoDTO.setProductName(deviceCachedInfo.getProductName());
        userDeviceDetailInfoDTO.setProductType(deviceCachedInfo.getProductType());
        userDeviceDetailInfoDTO.setIp(deviceCachedInfo.getIp());
        userDeviceDetailInfoDTO.setMac(deviceCachedInfo.getMac());
        userDeviceDetailInfoDTO.setCurrentPresetName(deviceCachedInfo.getCurrentPresetName());
        userDeviceDetailInfoDTO.setPresets(deviceCachedInfo.getPresets());
        userDeviceDetailInfoDTO.setNetworkStatus(networkStatus);
        userDeviceDetailInfoDTO.setProperties(deviceCachedInfo.getProperties());

        return userDeviceDetailInfoDTO;
    }

}
