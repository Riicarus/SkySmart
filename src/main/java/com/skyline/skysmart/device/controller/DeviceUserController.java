package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.dto.UserDeviceDetailInfoDTO;
import com.skyline.skysmart.device.entity.dto.UserDeviceInfoDTO;
import com.skyline.skysmart.device.service.IUserDeviceRelationDBService;
import com.skyline.skysmart.user.shiro.AssertPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * [FEATURE INFO]<br/>
 * device user controller
 *
 * @author Skyline
 * @create 2022/7/27 15:41
 * @since 1.0.0
 */
@RestController
@Api
public class DeviceUserController {

    private IUserDeviceRelationDBService userDeviceRelationDBService;

    private UserDeviceRelationDataConverter userDeviceRelationDataConverter;

    @Autowired
    public void setUserDeviceRelationDBService(IUserDeviceRelationDBService userDeviceRelationDBService) {
        this.userDeviceRelationDBService = userDeviceRelationDBService;
    }

    @Autowired
    public void setUserDeviceRelationDataConverter(UserDeviceRelationDataConverter userDeviceRelationDataConverter) {
        this.userDeviceRelationDataConverter = userDeviceRelationDataConverter;
    }

    @GetMapping("/user/{uid}/device")
    @ApiOperation("get all device info of this user")
    public ResponseResult<ArrayList<UserDeviceInfoDTO>> listAllDevice(
            @PathVariable("uid") String uid
    ) {
        AssertPermission.single("device:*:" + uid);

        ArrayList<UserDeviceInfoDTO> infoList = userDeviceRelationDBService.listDeviceUserInfoDTO(uid);

        return ResponseResult.success(infoList);
    }

    @GetMapping("/user/{uid}/device/online")
    @ApiOperation("get user's online device")
    public ResponseResult<ArrayList<UserDeviceInfoDTO>> listOnlineDevice(
            @PathVariable("uid") String uid
    ) {
        AssertPermission.single("device:*:" + uid);

        ArrayList<UserDeviceInfoDTO> infoList = userDeviceRelationDBService.listOnlineDeviceUserInfoDTO(uid);

        return ResponseResult.success(infoList);
    }

    @GetMapping("/user/{uid}/device/{deviceId}/detail")
    @ApiOperation("get one user's device detail info")
    public ResponseResult<UserDeviceDetailInfoDTO> getDeviceUserDetailInfoDTO(
            @PathVariable("uid") String uid,
            @PathVariable("deviceId") String deviceId
    ) {
        AssertPermission.single("device:*:" + uid);

        UserDeviceDetailInfoDTO userDeviceDetailInfoDTO = userDeviceRelationDBService.getDeviceUserDetailInfoDTO(deviceId);

        return ResponseResult.success(userDeviceDetailInfoDTO);
    }
}
