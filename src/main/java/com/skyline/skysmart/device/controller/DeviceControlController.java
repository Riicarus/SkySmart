package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.data.dto.DeviceInternetInfo;
import com.skyline.skysmart.device.service.impls.DeviceControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * [FEATURE INFO]<br/>
 * device control request controller
 *
 * @author Skyline
 * @create 2022/6/20 0:08
 * @since 1.0.0
 */
@Api(tags = {"device control request controller"})
@RestController
public class DeviceControlController {

    private DeviceControlService deviceControlService;

    @Autowired
    public void setDeviceControlService(DeviceControlService deviceControlService) {
        this.deviceControlService = deviceControlService;
    }

    @ApiOperation("register device internet information")
    @PostMapping(value = "/device/register", produces = {"application/json"})
    public ResponseResult<String> register(
            @RequestBody DeviceInternetInfo info
    ) {
        if (info.getDeviceId() == null || info.getIP() == null || info.getPort() == null) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        deviceControlService.register(info);
        return ResponseResult.success("Device register succeeded!");
    }

    @ApiOperation("turn service to work status")
    @PostMapping(value = "/device/control/on", produces = {"application/json"})
    public ResponseResult<String> turnOn(
            @RequestParam("deviceId") String deviceId,
            @RequestParam(value = "time", required = false) Long time
    ) {
        if (!StringUtils.hasLength(deviceId)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        deviceControlService.turnOn(deviceId, time);

        return ResponseResult.success("Turn on device succeeded!");
    }

    @ApiOperation("turn service to sleep status")
    @PostMapping(value = "/device/control/off", produces = {"application/json"})
    public ResponseResult<String> turnOff(
            @RequestParam("deviceId") String deviceId,
            @RequestParam(value = "time", required = false) Long time
    ) {
        if (!StringUtils.hasLength(deviceId)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        deviceControlService.turnOff(deviceId, time);

        return ResponseResult.success("Turn off device succeeded!");
    }
}
