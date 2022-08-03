package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.device.entity.dto.UserDeviceDetailInfoDTO;
import com.skyline.skysmart.device.entity.dto.UserDeviceInfoDTO;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * user device relation db service interface
 *
 * @author Skyline
 * @create 2022/7/27 9:26
 * @since 1.0.0
 */
public interface IUserDeviceRelationDBService {

    UserDeviceRelationDAO getRelationDAO(String relationId);

    IUserDeviceRelationBO getRelationBO(String relationId);

    IUserDeviceRelationBO getRelationBOByDeviceId(String deviceId);

    /**
     * list all user's device
     *
     * @param uid String, uid
     * @return ArrayList of UserDeviceInfoDTO
     */
    ArrayList<UserDeviceInfoDTO> listDeviceUserInfoDTO(String uid);

    /**
     * list all user's online device
     *
     * @param uid String, uid
     * @return ArrayList of DeviceUserInfoSTO
     */
    ArrayList<UserDeviceInfoDTO> listOnlineDeviceUserInfoDTO(String uid);

    /**
     * get device user detail info dto
     *
     * @param deviceId String, deviceId
     * @return UserDeviceDetailInfoDTO
     */
    UserDeviceDetailInfoDTO getDeviceUserDetailInfoDTO(String deviceId);

}
