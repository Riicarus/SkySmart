package com.skyline.skysmart.user.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyline.skysmart.user.entity.bo.interfaces.IUserBO;
import com.skyline.skysmart.user.entity.converter.UserDataConverter;
import com.skyline.skysmart.user.entity.dao.UserDAO;
import com.skyline.skysmart.user.entity.dto.UserAddParam;
import com.skyline.skysmart.user.entity.dto.UserLoginDTO;
import com.skyline.skysmart.user.mapper.UserMapper;
import com.skyline.skysmart.user.service.interfaces.IUserService;
import com.skyline.skysmart.core.enums.RedisKeyPrefix;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserDataConverter(UserDataConverter userDataConverter) {
        this.userDataConverter = userDataConverter;
    }

    @Override
    public IUserBO getUserBOByUid(String uid) {
        UserDAO userDAO = userMapper.selectById(uid);

        return userDataConverter.constructUserBO(userDAO);
    }

    @Override
    public IUserBO getUserBOByUsername(String username) {
        QueryWrapper<UserDAO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        UserDAO userDAO = userMapper.selectOne(userQueryWrapper);

        return userDataConverter.constructUserBO(userDAO);
    }

    /**
     * Get UserBO by email
     *
     * @param email String
     * @return IUserBO
     */
    @Override
    public IUserBO getUserBOByEmail(String email) {
        QueryWrapper<UserDAO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email", email);
        UserDAO userDAO = userMapper.selectOne(userQueryWrapper);

        return userDataConverter.constructUserBO(userDAO);
    }

    /**
     * UserDAO login with email and verycode
     *
     * @param email    String
     * @param verycode String
     * @return UserLoginDTO
     */
    @Override
    public UserLoginDTO loginByEmail(String email, String verycode) {
        IUserBO userBO = getUserBOByEmail(email);
        userBO.assertUserNotEmpty();

        String code = (String) redisTemplate.opsForValue().get(RedisKeyPrefix.EMAIL_LOGIN_VERYCODE.getKeyPrefix() + email);
        if (!verycode.equals(code)) {
            Asserts.fail(ResultCode.FAILED);
        }

        return userDataConverter.castToUserLoginDTO(userBO);
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

        return userDataConverter.castToUserLoginDTO(userBO);
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

    /**
     * change password with uid
     *
     * @param uid      String
     * @param password String
     */
    @Override
    public void changePassword(String uid, String password) {
        IUserBO userBO = getUserBOByUid(uid);

        userBO.setPassword(password);
        int res = userMapper.updateById(userBO.getUser());
        if (res != 1) {
            Asserts.fail(ResultCode.FAILED);
        }
    }

    /**
     * Change username with uid
     *
     * @param uid      String
     * @param username String
     */
    @Override
    public void changeUsername(String uid, String username) {
        IUserBO userBO = getUserBOByUid(uid);

        userBO.setUsername(username);
        int res = userMapper.updateById(userBO.getUser());
        if (res != 1) {
            Asserts.fail(ResultCode.FAILED);
        }
    }
}
