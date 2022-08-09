package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.service.IDeviceDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device data controller
 *
 * @author Skyline
 * @create 2022-8-9 18:15
 * @since 1.0.0
 */
@Api
@RestController
public class DeviceDataController {

    private IDeviceDataService deviceDataService;

    @Autowired
    public void setDeviceDataService(IDeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }

    @GetMapping("/device/{gatewayId}/sensor/data")
    @ApiOperation("get all sensor's data under this gateway")
    public ResponseResult<HashMap<String, HashMap<String, String>>> listAllSensorData(
            @PathVariable("gatewayId") String gatewayId
    ) {
        HashMap<String, HashMap<String, String>> sensorDataMap = deviceDataService.listAllSensorData(gatewayId);

        return ResponseResult.success(sensorDataMap);
    }

    @GetMapping("/device/{gatewayId}/sensor/info")
    @ApiOperation("get all sensors' info under this gateway")
    public ResponseResult<ArrayList<String>> listAllSensorInfo(
            @PathVariable("gatewayId") String gatewayId
    ) {
        ArrayList<String> sensorInfoList = deviceDataService.listAllSensorInfo(gatewayId);

        return ResponseResult.success(sensorInfoList);
    }
}
