package com.skyline.skysmart.user.entity.bo.interfaces;

import com.skyline.skysmart.user.entity.dao.UserDAO;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * UserBO interface
 *
 * @author Skyline
 * @create 2022/6/12 17:39
 * @since 1.0.0
 */
public interface IUserBO {

    void mapUser(UserDAO userDAO);

    UserDAO getUser();

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
