package com.skyline.skysmart.device.data.bo.impls;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.data.bo.interfaces.IDeviceBO;
import com.skyline.skysmart.device.data.dao.DeviceDAO;

/**
 * [FEATURE INFO]<br/>
 * DeviceBO
 *
 * @author Skyline
 * @create 2022/6/17 0:20
 * @since 1.0.0
 */
public class DeviceBO implements IDeviceBO {

    private DeviceDAO deviceDAO;

    /**
     * Set DeviceBO's DeviceDAO
     *
     * @param deviceDAO DeviceDAO
     */
    @Override
    public void mapDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    /**
     * Get DeviceBO's DeviceDAO
     *
     * @return DeviceDAO
     */
    @Override
    public DeviceDAO getDeviceDAO() {
        assertDeviceNotEmpty();
        return this.deviceDAO;
    }

    /**
     * Assert Device not empty
     */
    @Override
    public void assertDeviceNotEmpty() {
        if (this.deviceDAO == null) {
            Asserts.fail(ResultCode.NULL);
        }
    }
}
