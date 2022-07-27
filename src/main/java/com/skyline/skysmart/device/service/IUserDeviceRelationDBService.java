package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.bo.IUserDeviceRelationBO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;

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

}
