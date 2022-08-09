package com.skyline.skysmart.device.entity.vo;

import com.skyline.skysmart.device.entity.dao.DeviceDAO;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import com.skyline.skysmart.device.entity.dao.UserDeviceRelationDAO;
import com.skyline.skysmart.user.entity.dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * device info cached in redis
 *
 * @author Skyline
 * @create 2022/7/27 10:31
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCachedInfo {

    private UserDeviceRelationDAO userDeviceRelationDAO;

    private UserDAO userDAO;

    private DeviceDAO deviceDAO;

    private ProductDAO productDAO;

    private String ip;

    private String mac;

    private String gatewayDeviceId;

    private String authToken;

}
