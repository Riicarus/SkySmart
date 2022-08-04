package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.entity.message.impl.DeviceReportMessage;
import com.skyline.skysmart.device.service.IDeviceReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * [FEATURE INFO]<br/>
 * device report controller
 *
 * @author Skyline
 * @create 2022-8-4 20:21
 * @since 1.0.0
 */
@Api
@RestController
public class DeviceReportController {

    private IDeviceReportService deviceReportService;

    @Autowired
    public void setDeviceReportService(IDeviceReportService deviceReportService) {
        this.deviceReportService = deviceReportService;
    }

    @PostMapping("/device/report")
    @ApiOperation("handle device report message")
    public ResponseResult<Boolean> handleDeviceReport(
            @RequestBody DeviceReportMessage deviceReportMessage
    ) {
        deviceReportService.handleReport(deviceReportMessage);

        return ResponseResult.success(true);
    }

}
