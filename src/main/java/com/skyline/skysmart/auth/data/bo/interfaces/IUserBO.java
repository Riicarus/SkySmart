package com.skyline.skysmart.auth.data.bo.interfaces;

import com.skyline.skysmart.auth.data.dao.User;
import com.skyline.skysmart.device.data.devices.Device;

/**
 * [FEATURE INFO]<br/>
 * UserBO interface
 *
 * @author Skyline
 * @create 2022/6/12 17:39
 * @since 1.0.0
 */
public interface IUserBO {

    void mapUser(User user);

    User getUser();

    void mapDevice(Device device);

    Device getDevice(String name);

    String getUid();

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getSalt();

    void assertUserNotEmpty();

}
