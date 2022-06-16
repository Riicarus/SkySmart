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
     * Get UserBO by email
     *
     * @param email String
     * @return IUserBO
     */
    IUserBO getUserBOByEmail(String email);

    /**
     * user login with username and password
     *
     * @param username String
     * @param password String
     * @return UserLoginDTO
     */
    UserLoginDTO login(String username, String password);

    /**
     * User login with email and verycode
     *
     * @param email String
     * @param verycode String
     * @return UserLoginDTO
     */
    UserLoginDTO loginByEmail(String email, String verycode);

    /**
     * user register
     *
     * @param userAddParam UserAddParam
     */
    void register(UserAddParam userAddParam);

    /**
     * change password with uid
     *
     * @param uid String
     * @param password String
     */
    void changePassword(String uid, String password);

    /**
     * Change username with uid
     *
     * @param uid String
     * @param username String
     */
    void changeUsername(String uid, String username);
}
