package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.message.IDeviceLinkInMessage;
import com.skyline.skysmart.device.entity.message.IDeviceUnlinkMessage;

/**
 * [FEATURE INFO]<br/>
 * device link manager interface
 *
 * @author Skyline
 * @create 2022/7/26 12:36
 * @since 1.0.0
 */
public interface IDeviceLinkManager {

    /**
     * manage device link in request, auth device and log some info here through IDeviceMessageManager<br/>
     * 1. auth device <br/>
     * 2. log message <br/>
     *
     * @param deviceLinkInMessage IDeviceLinkInMessage
     * @return boolean, is linked in
     */
    boolean linkIn(IDeviceLinkInMessage deviceLinkInMessage);

    /**
     * auth device auth token, determine whether it's allowed
     *
     * @param ip String
     * @param mac String
     * @param authToken String
     * @return boolean, is allowed
     */
    boolean authDevice(String ip, String mac, String authToken);

    /**
     * manage device unlink request <br/>
     * 1. auth device <br/>
     * 2. log message <br/>
     *
     * @param deviceUnlinkMessage IDeviceUnlinkMessage
     */
    void unlink(IDeviceUnlinkMessage deviceUnlinkMessage);

}
