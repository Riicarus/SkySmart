package com.skyline.skysmart.device.service.impl;

import com.skyline.skysmart.device.service.IDeviceLinkManager;
import com.skyline.skysmart.log.ILogManager;
import com.skyline.skysmart.message.entity.IDeviceLinkInMessage;
import com.skyline.skysmart.message.entity.IDeviceUnlinkMessage;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * [FEATURE INFO]<br/>
 * device link manager
 *
 * @author Skyline
 * @create 2022/7/26 21:13
 * @since 1.0.0
 */
@Service
public class DeviceLinkManager implements IDeviceLinkManager {

    private ILogManager logManager;

    @Autowired
    public void setLogManager(ILogManager logManager) {
        this.logManager = logManager;
    }

    /**
     * manage device link in request, auth device and log some info here through IDeviceMessageManager<br/>
     * 1. auth device <br/>
     * 2. log message <br/>
     *
     * @param deviceLinkInMessage IDeviceLinkInMessage
     * @return boolean, is linked in
     */
    @Override
    public boolean linkIn(IDeviceLinkInMessage deviceLinkInMessage) {
        String ip = deviceLinkInMessage.getIp();
        String mac = deviceLinkInMessage.getMac();
        String authToken = deviceLinkInMessage.getAuthToken();

        boolean isAllowed = authDevice(ip, mac, authToken);
        if (!isAllowed) {
            return false;
        }

        logManager.logInfo(deviceLinkInMessage);

        return true;
    }

    /**
     * auth device auth token, determine whether it's allowed
     *
     * @param ip        String
     * @param mac       String
     * @param authToken String
     * @return boolean, is allowed
     */
    @Override
    public boolean authDevice(String ip, String mac, String authToken) {
        Md5Hash md5Hash = new Md5Hash(ip, mac, 1024);

        return authToken.equals(md5Hash.toString());
    }

    /**
     * manage device unlink request <br/>
     * 1. auth device <br/>
     * 2. log message <br/>
     *
     * @param deviceUnlinkMessage IDeviceUnlinkMessage
     */
    @Override
    public void unlink(IDeviceUnlinkMessage deviceUnlinkMessage) {

    }
}
