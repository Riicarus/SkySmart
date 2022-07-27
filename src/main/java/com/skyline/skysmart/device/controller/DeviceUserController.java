package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.entity.converter.UserDeviceRelationDataConverter;
import com.skyline.skysmart.device.entity.dto.DeviceUserInfoDTO;
import com.skyline.skysmart.device.service.IUserDeviceRelationDBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseResult<ArrayList<DeviceUserInfoDTO>> listAllDevice(
            @PathVariable("uid") String uid
    ) {
        ArrayList<DeviceUserInfoDTO> infoList = userDeviceRelationDBService.listDeviceUserInfoDTO(uid);

        return ResponseResult.success(infoList);
    }
}
