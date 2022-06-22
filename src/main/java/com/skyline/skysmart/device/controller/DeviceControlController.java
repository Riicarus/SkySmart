package com.skyline.skysmart.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.data.dto.DeviceInternetInfo;
import com.skyline.skysmart.device.data.dto.InstructionUnit;
import com.skyline.skysmart.device.service.impls.DeviceControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

/**
 * [FEATURE INFO]<br/>
 * device control request controller
 *
 * @author Skyline
 * @create 2022/6/20 0:08
 * @since 1.0.0
 */
@Api(tags = {"DeviceControlController"})
@RestController
public class DeviceControlController {

    private DeviceControlService deviceControlService;

    @Autowired
    public void setDeviceControlService(DeviceControlService deviceControlService) {
        this.deviceControlService = deviceControlService;
    }

    @ApiOperation("register device internet information")
    @PostMapping("/device/register")
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
    @PostMapping("/device/control/on")
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
    @PostMapping("/device/control/off")
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

    @ApiOperation("handle multi instructions request")
    @PostMapping("/device/control/multi")
    public ResponseResult<String> multiInstructions(
            @RequestParam("deviceId") String deviceId,
            @RequestParam("params") String paramsJson
    ) {
        if (!StringUtils.hasLength(deviceId) || !StringUtils.hasLength(paramsJson)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        Queue<InstructionUnit> unitQueue = JSONObject.parseObject(paramsJson, new TypeReference<Queue<InstructionUnit>>(){});
        deviceControlService.handleMulti(deviceId, unitQueue);

        return ResponseResult.success("Handle multi instructions succeeded!");
    }
}
