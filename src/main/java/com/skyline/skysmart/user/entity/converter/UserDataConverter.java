package com.skyline.skysmart.user.entity.converter;

import com.skyline.skysmart.user.entity.bo.impls.UserBO;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;
import com.skyline.skysmart.user.entity.dao.UserDAO;
import com.skyline.skysmart.user.entity.dto.UserAddParam;
import com.skyline.skysmart.user.entity.dto.UserLoginDTO;
import com.skyline.skysmart.user.util.JwtUtils;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * [FEATURE INFO]<br/>
 * UserDataConverter
 *
 * @author Skyline
 * @create 2022/6/12 17:35
 * @since 1.0.0
 */
@Component
public class UserDataConverter {

    private static final UserDataConverter INSTANCE = new UserDataConverter();

    private UserDataConverter() {}

    public static UserDataConverter getConverter() {
        return INSTANCE;
    }

    /**
     * construct IUserBO
     *
     * @param userDAO UserDAO
     * @return IUserBO
     */
    public IUserBO constructUserBO(UserDAO userDAO) {
        IUserBO userBO = new UserBO();
        userBO.mapUserDAO(userDAO);
        return userBO;
    }

    /**
     * cast UserBO to UserLoginDTO
     *
     * @param userBO IUserBO
     * @return UserLoginDTO
     */
    public UserLoginDTO castToUserLoginDTO(IUserBO userBO) {
        if (userBO == null) {
            return new UserLoginDTO();
        }

        // sign token
        Map<String, String> payload = new HashMap<>();
        payload.put("account", userBO.getUid());
        payload.put("username", userBO.getUsername());
        payload.put("loginType", "user");
        String token = JwtUtils.sign(payload);

        // add fields
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUid(userBO.getUid());
        userLoginDTO.setUsername(userBO.getUsername());
        userLoginDTO.setEmail(userBO.getEmail());
        userLoginDTO.setToken(token);

        return userLoginDTO;
    }


    /**
     * cast userAddParam to IUserBO
     *
     * @param userAddParam UserAddParam
     * @return IUserBO
     */
    public IUserBO castToIUserBO(UserAddParam userAddParam, Boolean isAdmin) {
        if (userAddParam == null) {
            Asserts.fail(ResultCode.NULL);
        }

        UserDAO userDAO = new UserDAO();
        userDAO.setUid(UUID.randomUUID().toString());
        userDAO.setUsername(userAddParam.getUsername());
        userDAO.setSalt(String.valueOf(System.currentTimeMillis()));
        userDAO.setEmail("");
        userDAO.setAdmin(isAdmin);

        Md5Hash md5Hash = new Md5Hash(userAddParam.getPassword(), userDAO.getSalt(), 1024);
        String md5Password = md5Hash.toHex();
        userDAO.setPassword(md5Password);

        return constructUserBO(userDAO);
    }

}
