package com.skyline.skysmart.device.entity.bo;

import com.skyline.skysmart.device.entity.dao.DeviceDAO;
import com.skyline.skysmart.device.entity.model.IDevice;

/**
 * [FEATURE INFO]<br/>
 * device bo interface
 *
 * @author Skyline
 * @create 2022/7/26 17:09
 * @since 1.0.0
 */
public interface IDeviceBO extends IDevice {

    void mapDeviceDAO(DeviceDAO deviceDAO);

    DeviceDAO getDeviceDAO();

    void mapProductBO(IProductBO productBO);

    IProductBO getProductBO();

    void setCreateTime(Long createTime);

    Long getCreateTime();

    void setLastRegisterTime(Long lastRegisterTime);

    Long getLastRegisterTime();

    void assertDeviceDAONotNull();

    void assertProductBONotNull();

}
