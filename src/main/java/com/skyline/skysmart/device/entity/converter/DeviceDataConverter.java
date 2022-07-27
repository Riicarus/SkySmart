package com.skyline.skysmart.device.entity.converter;

import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.bo.impl.DeviceBO;
import com.skyline.skysmart.device.entity.dao.DeviceDAO;
import org.springframework.stereotype.Component;

/**
 * [FEATURE INFO]<br/>
 * device data converter
 *
 * @author Skyline
 * @create 2022/7/26 23:01
 * @since 1.0.0
 */
@Component
public class DeviceDataConverter {

    public IDeviceBO castToDeviceBO(DeviceDAO deviceDAO, IProductBO productBO) {
        if (deviceDAO == null || productBO == null) {
            return new DeviceBO();
        }

        IDeviceBO deviceBO = new DeviceBO();
        deviceBO.mapDeviceDAO(deviceDAO);
        deviceBO.mapProductBO(productBO);

        return deviceBO;
    }

}
