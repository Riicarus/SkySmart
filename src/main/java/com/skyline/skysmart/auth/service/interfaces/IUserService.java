package com.skyline.skysmart.auth.service.interfaces;

import com.skyline.skysmart.auth.data.bo.interfaces.IUserBO;
import com.skyline.skysmart.auth.data.dto.UserAddParam;
import com.skyline.skysmart.auth.data.dto.UserLoginDTO;

/**
 * [FEATURE INFO]<br/>
 * user service interface
 *
 * @author Skyline
 * @create 2022/6/12 17:20
 * @since 1.0.0
 */
public interface IUserService {

    IUserBO getUserBOByUid(String uid);

    IUserBO getUserBOByUsername(String username);

    /**
     * user login with username and password
     *
     * @param username String
     * @param password String
     * @return UserLoginDTO
     */
    UserLoginDTO login(String username, String password);


    /**
     * user register
     *
     * @param userAddParam UserAddParam
     */
    void register(UserAddParam userAddParam);

}
