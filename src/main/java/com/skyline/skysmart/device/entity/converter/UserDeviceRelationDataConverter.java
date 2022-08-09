package com.skyline.skysmart.device.entity.converter;

import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.bo.impl.DeviceBO;
import com.skyline.skysmart.device.entity.bo.impl.ProductBO;
import com.skyline.skysmart.device.entity.bo.impl.UserDeviceRelationBO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.dto.UserDeviceDetailInfoDTO;
import com.skyline.skysmart.device.entity.dto.UserDeviceInfoDTO;
import com.skyline.skysmart.device.entity.message.IDeviceRegisterMessage;
import com.skyline.skysmart.device.entity.vo.DeviceCachedInfo;
import com.skyline.skysmart.user.entity.bo.impls.UserBO;
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

    public IUserDeviceRelationBO castToUserDeviceRelationBO(DeviceCachedInfo deviceCachedInfo) {
        IUserDeviceRelationBO relationBO = new UserDeviceRelationBO();
        IDeviceBO deviceBO = new DeviceBO();
        IProductBO productBO = new ProductBO();
        IUserBO userBO = new UserBO();

        productBO.mapProductDAO(deviceCachedInfo.getProductDAO());
        deviceBO.mapDeviceDAO(deviceCachedInfo.getDeviceDAO());
        deviceBO.mapProductBO(productBO);
        deviceBO.setIp(deviceCachedInfo.getIp());
        deviceBO.setMac(deviceCachedInfo.getMac());
        deviceBO.setGatewayDeviceId(deviceCachedInfo.getGatewayDeviceId());
        deviceBO.setAuthToken(deviceCachedInfo.getAuthToken());
        relationBO.mapDeviceBO(deviceBO);

        userBO.mapUserDAO(deviceCachedInfo.getUserDAO());
        relationBO.mapUserBO(userBO);

        relationBO.mapUserDeviceRelationDAO(deviceCachedInfo.getUserDeviceRelationDAO());

        return relationBO;
    }

    public DeviceCachedInfo castToDeviceCachedInfo(IUserDeviceRelationBO userDeviceRelationBO, IDeviceRegisterMessage deviceRegisterMessage) {
        DeviceCachedInfo deviceCachedInfo = new DeviceCachedInfo();

        deviceCachedInfo.setDeviceDAO(userDeviceRelationBO.getDeviceBO().getDeviceDAO());
        deviceCachedInfo.setUserDeviceRelationDAO(userDeviceRelationBO.getUserDeviceRelationDAO());
        deviceCachedInfo.setProductDAO(userDeviceRelationBO.getDeviceBO().getProductBO().getProductDAO());
        deviceCachedInfo.setUserDAO(userDeviceRelationBO.getUserBO().getUserDAO());
        deviceCachedInfo.setIp(deviceRegisterMessage.getIp());
        deviceCachedInfo.setMac(deviceRegisterMessage.getMac());
        deviceCachedInfo.setGatewayDeviceId(deviceRegisterMessage.getGatewayDeviceId());
        deviceCachedInfo.setAuthToken(deviceRegisterMessage.getAuthToken());
        deviceCachedInfo.getDeviceDAO().setLastRegisterTime(deviceRegisterMessage.getRegisterTime());

        return deviceCachedInfo;
    }

    public DeviceCachedInfo castToDeviceCachedInfo(IUserDeviceRelationBO userDeviceRelationBO) {
        DeviceCachedInfo deviceCachedInfo = new DeviceCachedInfo();

        deviceCachedInfo.setDeviceDAO(userDeviceRelationBO.getDeviceBO().getDeviceDAO());
        deviceCachedInfo.setUserDeviceRelationDAO(userDeviceRelationBO.getUserDeviceRelationDAO());
        deviceCachedInfo.setProductDAO(userDeviceRelationBO.getDeviceBO().getProductBO().getProductDAO());
        deviceCachedInfo.setUserDAO(userDeviceRelationBO.getUserBO().getUserDAO());
        deviceCachedInfo.setIp(userDeviceRelationBO.getDeviceBO().getIp());
        deviceCachedInfo.setMac(userDeviceRelationBO.getDeviceBO().getMac());
        deviceCachedInfo.setGatewayDeviceId(userDeviceRelationBO.getDeviceBO().getGatewayDeviceId());
        deviceCachedInfo.setAuthToken(userDeviceRelationBO.getDeviceBO().getAuthToken());

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

    public UserDeviceDetailInfoDTO castToDeviceUserDetailInfoDTO(IUserDeviceRelationBO userDeviceRelationBO, Boolean networkStatus) {
        UserDeviceDetailInfoDTO userDeviceDetailInfoDTO = new UserDeviceDetailInfoDTO();
        userDeviceDetailInfoDTO.setUid(userDeviceRelationBO.getUid());
        userDeviceDetailInfoDTO.setDeviceId(userDeviceRelationBO.getDeviceId());
        userDeviceDetailInfoDTO.setDeviceName(userDeviceRelationBO.getDeviceBO().getName());
        userDeviceDetailInfoDTO.setAliasName(userDeviceRelationBO.getAliasName());
        userDeviceDetailInfoDTO.setProductId(userDeviceRelationBO.getDeviceBO().getProductId());
        userDeviceDetailInfoDTO.setProductName(userDeviceRelationBO.getDeviceBO().getProductBO().getName());
        userDeviceDetailInfoDTO.setProductType(userDeviceRelationBO.getDeviceBO().getProductBO().getType());
        if (networkStatus) {
            userDeviceDetailInfoDTO.setIp(userDeviceRelationBO.getDeviceBO().getIp());
            userDeviceDetailInfoDTO.setMac(userDeviceRelationBO.getDeviceBO().getMac());
        } else {
            userDeviceDetailInfoDTO.setIp("offline");
            userDeviceDetailInfoDTO.setMac("offline");
        }
        userDeviceDetailInfoDTO.setCurrentPresetName(userDeviceRelationBO.getCurrentPresetName());
        userDeviceDetailInfoDTO.setPresets(userDeviceRelationBO.getPresets());
        userDeviceDetailInfoDTO.setNetworkStatus(networkStatus);
        userDeviceDetailInfoDTO.setProperties(userDeviceRelationBO.getProperties());

        return userDeviceDetailInfoDTO;
    }

}
