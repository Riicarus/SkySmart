package com.skyline.skysmart.user.entity.bo.interfaces;

import com.skyline.skysmart.user.entity.dao.UserDAO;

/**
 * [FEATURE INFO]<br/>
 * UserBO interface
 *
 * @author Skyline
 * @create 2022/6/12 17:39
 * @since 1.0.0
 */
public interface IUserBO {

    void mapUserDAO(UserDAO userDAO);

    UserDAO getUserDAO();

    String getUid();

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getSalt();

    Boolean isAdmin();

    void setAdmin(Boolean isAdmin);

    void assertUserNotEmpty();

}
