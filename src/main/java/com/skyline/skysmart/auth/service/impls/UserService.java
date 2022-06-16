package com.skyline.skysmart.auth.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyline.skysmart.auth.data.bo.interfaces.IUserBO;
import com.skyline.skysmart.auth.data.converter.UserDataConverter;
import com.skyline.skysmart.auth.data.dao.User;
import com.skyline.skysmart.auth.data.dto.UserAddParam;
import com.skyline.skysmart.auth.data.dto.UserLoginDTO;
import com.skyline.skysmart.auth.mapper.UserMapper;
import com.skyline.skysmart.auth.service.interfaces.IUserService;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * [FEATURE INFO]<br/>
 * user service
 *
 * @author Skyline
 * @create 2022/6/12 17:20
 * @since 1.0.0
 */
@Service
public class UserService implements IUserService {

    private UserMapper userMapper;

    private UserDataConverter userDataConverter;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserDataConverter(UserDataConverter userDataConverter) {
        this.userDataConverter = userDataConverter;
    }

    @Override
    public IUserBO getUserBOByUid(String uid) {
        User user = userMapper.selectById(uid);

        return userDataConverter.getUserBO(user);
    }

    @Override
    public IUserBO getUserBOByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);

        return userDataConverter.getUserBO(user);
    }

    /**
     * user login with username and password
     *
     * @param username String
     * @param password String
     * @return UserLoginDTO
     */
    @Override
    public UserLoginDTO login(String username, String password) {
        IUserBO userBO = getUserBOByUsername(username);

        String _password = userBO.getPassword();
        String salt = userBO.getSalt();

        Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
        String md5Password = md5Hash.toHex();

        if (!_password.equals(md5Password)) {
            Asserts.fail(ResultCode.FAILED);
        }

        return userDataConverter.getUserLoginDTO(userBO);
    }


    /**
     * user register
     *
     * @param userAddParam UserAddParam
     */
    @Override
    public void register(UserAddParam userAddParam) {
        IUserBO userBO = userDataConverter.castToIUserBO(userAddParam);

        int res = userMapper.insert(userBO.getUser());
        if (res != 1) {
            Asserts.fail(ResultCode.FAILED);
        }
    }
}
