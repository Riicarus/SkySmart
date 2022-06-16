package com.skyline.skysmart.auth.controller;

import com.skyline.skysmart.auth.data.dto.UserAddParam;
import com.skyline.skysmart.auth.data.dto.UserLoginDTO;
import com.skyline.skysmart.auth.mapper.UserMapper;
import com.skyline.skysmart.auth.service.interfaces.IUserService;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * [FEATURE INFO]<br/>
 * Control request of user
 *
 * @author Skyline
 * @create 2022/6/12 17:14
 * @since 1.0.0
 */
@RestController
@Api
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "user login")
    @PostMapping(value = "/user/login", produces = {"application/json"})
    public ResponseResult<UserLoginDTO> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        UserLoginDTO userLoginDTO = userService.login(username, password);
        return ResponseResult.success(userLoginDTO);
    }

    @ApiOperation(value = "user register")
    @PostMapping(value = "/user", produces = {"application/json"})
    public ResponseResult<String> register(
            @RequestBody UserAddParam userAddParam
    ) {
        if (userAddParam == null) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        userService.register(userAddParam);
        return ResponseResult.success("Register succeeded!");
    }
}
