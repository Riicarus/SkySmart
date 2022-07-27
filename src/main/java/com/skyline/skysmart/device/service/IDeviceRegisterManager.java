package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.message.IDeviceRegisterMessage;
import com.skyline.skysmart.device.entity.message.IDeviceUnregisterMessage;

/**
 * [FEATURE INFO]<br/>
 * device register manager interface
 *
 * @author Skyline
 * @create 2022/7/26 15:54
 * @since 1.0.0
 */
public interface IDeviceRegisterManager {

    /**
     * register device <br/>
     * 1. get related device info, status, config and others <br/>
     * 2. cache device info to redis <br/>
     * 3. log info <br/>
     *
     * @param deviceRegisterMessage IDeviceRegisterMessage
     */
    void register(IDeviceRegisterMessage deviceRegisterMessage);

    /**
     * unregister device <br/>
     * 1. remove device info from redis <br/>
     * 2. log info <br/>
     *
     * @param deviceUnregisterMessage IDeviceUnregisterMessage
     */
    void unregister(IDeviceUnregisterMessage deviceUnregisterMessage);

}
