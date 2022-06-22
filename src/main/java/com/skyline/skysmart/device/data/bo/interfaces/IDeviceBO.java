package com.skyline.skysmart.device.data.bo.interfaces;

import com.skyline.skysmart.device.data.dao.DeviceDAO;

/**
 * [FEATURE INFO]<br/>
 * device bo interface
 *
 * @author Skyline
 * @create 2022/6/17 0:49
 * @since 1.0.0
 */
public interface IDeviceBO {

    /**
     * Set DeviceBO's DeviceDAO
     *
     * @param deviceDAO DeviceDAO
     */
    void mapDeviceDAO(DeviceDAO deviceDAO);

    /**
     * Get DeviceBO's DeviceDAO
     *
     * @return DeviceDAO
     */
    DeviceDAO getDeviceDAO();

    /**
     * Assert Device not empty
     */
    void assertDeviceNotEmpty();

}
