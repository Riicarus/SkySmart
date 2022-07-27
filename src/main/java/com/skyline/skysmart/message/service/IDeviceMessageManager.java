package com.skyline.skysmart.message.service;

import com.skyline.skysmart.message.entity.IDeviceLinkInMessage;
import com.skyline.skysmart.message.entity.IDeviceUnlinkMessage;

/**
 * [FEATURE INFO]<br/>
 * device message manager interface
 *
 * @author Skyline
 * @create 2022/7/26 12:40
 * @since 1.0.0
 */
public interface IDeviceMessageManager {

    /**
     * log device link in message
     *
     * @param deviceLinkInMessage IDeviceLinkInMessage
     */
    void handleLinkInMessage(IDeviceLinkInMessage deviceLinkInMessage);

    /**
     * log device unlink message
     *
     * @param deviceUnlinkMessage IDeviceUnlinkMessage
     */
    void handleUnlinkMessage(IDeviceUnlinkMessage deviceUnlinkMessage);

}
