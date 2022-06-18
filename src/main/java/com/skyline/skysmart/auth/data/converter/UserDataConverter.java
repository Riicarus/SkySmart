package com.skyline.skysmart.auth.data.converter;

import com.skyline.skysmart.auth.data.bo.impls.UserBO;
import com.skyline.skysmart.auth.data.bo.interfaces.IUserBO;
import com.skyline.skysmart.auth.data.dao.User;
import com.skyline.skysmart.auth.data.dto.UserAddParam;
import com.skyline.skysmart.auth.data.dto.UserLoginDTO;
import com.skyline.skysmart.auth.util.JwtUtils;
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
     * @param user User
     * @return IUserBO
     */
    public IUserBO constructUserBO(User user) {
        IUserBO userBO = new UserBO();
        userBO.mapUser(user);
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
    public IUserBO castToIUserBO(UserAddParam userAddParam) {
        if (userAddParam == null) {
            Asserts.fail(ResultCode.NULL);
        }

        User user = new User();
        user.setUid(UUID.randomUUID().toString());
        user.setUsername(userAddParam.getUsername());
        user.setSalt(String.valueOf(System.currentTimeMillis()));
        user.setEmail("");

        Md5Hash md5Hash = new Md5Hash(userAddParam.getPassword(), user.getSalt(), 1024);
        String md5Password = md5Hash.toHex();
        user.setPassword(md5Password);

        return constructUserBO(user);
    }

}
