package com.skyline.skysmart.device.service.impl;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.device.entity.bo.IDeviceBO;
import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.converter.DeviceDataConverter;
import com.skyline.skysmart.device.entity.dao.DeviceDAO;
import com.skyline.skysmart.device.mapper.DeviceMapper;
import com.skyline.skysmart.device.service.IDeviceDBService;
import com.skyline.skysmart.device.service.IProductDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * [FEATURE INFO]<br/>
 * device db service
 *
 * @author Skyline
 * @create 2022/7/26 22:59
 * @since 1.0.0
 */
@Service
public class DeviceDBService implements IDeviceDBService {

    private DeviceMapper deviceMapper;

    private IProductDBService productDBService;

    private DeviceDataConverter deviceDataConverter;

    @Autowired
    public void setDeviceMapper(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Autowired
    public void setProductDBService(IProductDBService productDBService) {
        this.productDBService = productDBService;
    }

    @Autowired
    public void setDeviceDataConverter(DeviceDataConverter deviceDataConverter) {
        this.deviceDataConverter = deviceDataConverter;
    }

    @Override
    public DeviceDAO getDeviceDAO(String deviceId) {
        return deviceMapper.selectById(deviceId);
    }

    @Override
    public IDeviceBO getDeviceBO(String deviceId) {
        DeviceDAO deviceDAO = getDeviceDAO(deviceId);
        IProductBO productBO = productDBService.getProductBO(deviceDAO.getProductId());
        return deviceDataConverter.castToDeviceBO(deviceDAO, productBO);
    }

    @Override
    public void updateLastRegisterTime(String deviceId, Long lastRegisterTime) {
        IDeviceBO deviceBO = getDeviceBO(deviceId);
        deviceBO.setLastRegisterTime(lastRegisterTime);

        int res = deviceMapper.updateById(deviceBO.getDeviceDAO());
        if (res != 1) {
            Asserts.fail(ResultCode.FAILED);
        }
    }
}
