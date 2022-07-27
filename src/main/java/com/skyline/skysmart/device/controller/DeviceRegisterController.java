package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.service.IDeviceRegisterManager;
import com.skyline.skysmart.device.entity.message.impl.DeviceRegisterMessage;
import com.skyline.skysmart.device.entity.message.impl.DeviceUnregisterMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * [FEATURE INFO]<br/>
 * device register controller
 *
 * @author Skyline
 * @create 2022/7/27 9:00
 * @since 1.0.0
 */
@RestController
@Api
public class DeviceRegisterController {

    private IDeviceRegisterManager deviceRegisterManager;

    @Autowired
    public void setDeviceRegisterManager(IDeviceRegisterManager deviceRegisterManager) {
        this.deviceRegisterManager = deviceRegisterManager;
    }

    @PostMapping("/device/register")
    @ApiOperation("register device")
    public ResponseResult<String> register(
            @RequestBody DeviceRegisterMessage deviceRegisterMessage
    ) {
        deviceRegisterManager.register(deviceRegisterMessage);

        return ResponseResult.success("Register Succeeded!");
    }

    @PostMapping("/device/unregister")
    @ApiOperation("unregister device")
    public ResponseResult<String> unregister(
            @RequestBody DeviceUnregisterMessage deviceUnregisterMessage
    ) {
        deviceRegisterManager.unregister(deviceUnregisterMessage);

        return ResponseResult.success("Unregister Succeeded!");
    }
}
