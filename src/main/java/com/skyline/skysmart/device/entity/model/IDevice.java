package com.skyline.skysmart.device.entity.model;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * device interface
 *
 * @author Skyline
 * @create 2022/7/26 10:59
 * @since 1.0.0
 */
public interface IDevice {

    /**
     * device id
     *
     * @return String, device id
     */
    String getId();

    void setId(String id);

    /**
     * product id of device
     *
     * @return String product id
     */
    String getProductId();

    void setProductId(String productId);

    /**
     * device name
     *
     * @return String, device name
     */
    String getName();

    void setName(String name);

    /**
     * device ip
     *
     * @return String, device ip
     */
    String getIp();

    void setIp(String ip);

    /**
     * device mac address
     *
     * @return String, mac address
     */
    String getMac();

    void setMac(String mac);

    /**
     * device auth token, used to verify device
     *
     * @return String auth token
     */
    String getAuthToken();

    void setAuthToken(String authToken);

    /**
     * device properties of preset
     *
     * @return ArrayList, device properties
     */
    ArrayList<IProperty> getProperties();

    void setProperties(ArrayList<IProperty> properties);
}
