package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.service.IDeviceLinkManager;
import com.skyline.skysmart.device.service.IDeviceRegisterManager;
import com.skyline.skysmart.message.entity.impl.DeviceLinkInMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * [FEATURE INFO]<br/>
 * device link controller
 *
 * @author Skyline
 * @create 2022/7/26 22:01
 * @since 1.0.0
 */
@RestController
@Api
public class DeviceLinkController {

    private IDeviceRegisterManager deviceRegisterManager;
    private IDeviceLinkManager deviceLinkManager;

    @Autowired
    public void setDeviceRegisterManager(IDeviceRegisterManager deviceRegisterManager) {
        this.deviceRegisterManager = deviceRegisterManager;
    }

    @Autowired
    public void setDeviceLinkManager(IDeviceLinkManager deviceLinkManager) {
        this.deviceLinkManager = deviceLinkManager;
    }

    @PostMapping("/device/link")
    @ApiOperation("link in device")
    public ResponseResult<Boolean> linkIn(
            @RequestBody DeviceLinkInMessage deviceLinkInMessage
    ) {
       boolean isAllowed = deviceLinkManager.linkIn(deviceLinkInMessage);

       return ResponseResult.success(isAllowed);
    }

}
