package com.skyline.skysmart.device.data.converter;

import com.skyline.skysmart.device.data.bo.impls.DeviceBO;
import com.skyline.skysmart.device.data.bo.interfaces.IDeviceBO;
import com.skyline.skysmart.device.data.dao.DeviceDAO;
import org.springframework.stereotype.Component;

/**
 * [FEATURE INFO]<br/>
 * device data converter
 *
 * @author Skyline
 * @create 2022/6/17 16:09
 * @since 1.0.0
 */
@Component
public class DeviceDataConverter {

    private static final DeviceDataConverter INSTANCE = new DeviceDataConverter();

    private DeviceDataConverter() {}

    public static DeviceDataConverter getConverter() {
        return INSTANCE;
    }

    /**
     * construct IDeviceBO from DeviceDAO
     *
     * @param deviceDAO DeviceDAO
     * @return IDeviceBO
     */
    public IDeviceBO constructDeviceBO(DeviceDAO deviceDAO) {
        IDeviceBO deviceBO = new DeviceBO();
        deviceBO.mapDeviceDAO(deviceDAO);
        return deviceBO;
    }

}
