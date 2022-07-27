package com.skyline.skysmart.user.entity.bo.impls;

import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;
import com.skyline.skysmart.user.entity.dao.UserDAO;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * [FEATURE INFO]<br/>
 * UserBO, consists of all business info of userDAO, such as UserDAO, userDAO's devices, userDAO's preset
 *
 * @author Skyline
 * @create 2022/6/12 17:43
 * @since 1.0.0
 */
public class UserBO implements IUserBO {

    private UserDAO userDAO;

    @Override
    public void mapUser(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDAO getUser() {
        assertUserNotEmpty();
        return userDAO;
    }

    @Override
    public String getUid() {
        assertUserNotEmpty();
        return userDAO.getUid();
    }

    @Override
    public String getUsername() {
        assertUserNotEmpty();
        return userDAO.getUsername();
    }

    @Override
    public void setUsername(String username) {
        assertUserNotEmpty();
        userDAO.setUsername(username);
    }

    @Override
    public String getPassword() {
        assertUserNotEmpty();
        return userDAO.getPassword();
    }

    @Override
    public void setPassword(String password) {
        assertUserNotEmpty();

        Md5Hash md5Hash = new Md5Hash(password, userDAO.getSalt(), 1024);
        String md5Password = md5Hash.toHex();
        userDAO.setPassword(md5Password);
    }

    @Override
    public String getEmail() {
        assertUserNotEmpty();
        return userDAO.getEmail();
    }

    @Override
    public void setEmail(String email) {
        assertUserNotEmpty();
        userDAO.setEmail(email);
    }

    @Override
    public String getSalt() {
        assertUserNotEmpty();
        return userDAO.getSalt();
    }

    @Override
    public void assertUserNotEmpty() {
        if (userDAO == null) {
            Asserts.fail(ResultCode.NULL);
        }
    }
}
