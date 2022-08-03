package com.skyline.skysmart.user.controller;

import com.skyline.skysmart.user.entity.dto.UserAddParam;
import com.skyline.skysmart.user.entity.dto.UserLoginDTO;
import com.skyline.skysmart.user.service.interfaces.IUserService;
import com.skyline.skysmart.user.shiro.AssertPermission;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * [FEATURE INFO]<br/>
 * Control request of user
 *
 * @author Skyline
 * @create 2022/6/12 17:14
 * @since 1.0.0
 */
@Api(tags = {"UserController"})
@RestController
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation("user login")
    @PostMapping("/user/login")
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

    @ApiOperation("user login by email")
    @PostMapping("/user/login/email")
    public ResponseResult<UserLoginDTO> loginByEmail(
            @RequestParam("email") String email,
            @RequestParam("verycode") String verycode
    ) {
        if (!StringUtils.hasLength(email) || !StringUtils.hasLength(verycode)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        UserLoginDTO userLoginDTO = userService.loginByEmail(email, verycode);

        return ResponseResult.success(userLoginDTO);
    }

    @ApiOperation("user register")
    @PostMapping("/user")
    public ResponseResult<String> register(
            @RequestBody UserAddParam userAddParam
    ) {
        if (userAddParam == null) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        userService.register(userAddParam);
        return ResponseResult.success("Register succeeded!");
    }

    @ApiOperation("user register")
    @PostMapping("/user-admin")
    public ResponseResult<String> registerAdmin(
            @RequestBody UserAddParam userAddParam
    ) {
        if (userAddParam == null) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        userService.registerAdmin(userAddParam);
        return ResponseResult.success("Register succeeded!");
    }

    @ApiOperation("user change password after login")
    @PutMapping("/user/password")
    public ResponseResult<String> changePassword(
            @RequestParam("uid") String uid,
            @RequestParam("password") String password
    ) {
        AssertPermission.single("user:*:" + uid);

        if (!StringUtils.hasLength(uid) || !StringUtils.hasLength(password)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        userService.changePassword(uid, password);
        return ResponseResult.success("Change password succeeded!");
    }

    @ApiOperation("user change password before login")
    @PutMapping("/user/password/resection")
    public ResponseResult<String> resetPassword(
            @RequestParam("uid") String uid,
            @RequestParam("verycode") String verycode,
            @RequestParam("password") String password
    ) {
        if (!StringUtils.hasLength(uid) || !StringUtils.hasLength(verycode) || !StringUtils.hasLength(password)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        userService.changePassword(uid, password);
        return ResponseResult.success("Change password succeeded!");
    }

    @ApiOperation("change username by uid")
    @PutMapping("/user/username")
    public ResponseResult<String> changeUsername(
            @RequestParam("uid") String uid,
            @RequestParam("username") String username
    ) {
        AssertPermission.single("user:*:" + uid);

        if (!StringUtils.hasLength(uid) || !StringUtils.hasLength(username)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        userService.changeUsername(uid, username);
        return ResponseResult.success("Change username succeeded!");
    }
}
