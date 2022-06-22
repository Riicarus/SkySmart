package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.enums.ResultCode;
import com.skyline.skysmart.core.exception.Asserts;
import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.data.dto.SceneAddParam;
import com.skyline.skysmart.device.service.interfaces.ISceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * [FEATURE INFO]<br/>
 * scene controller
 *
 * @author Skyline
 * @create 2022/6/22 19:40
 * @since 1.0.0
 */
@Api(tags = {"SceneController"})
@RestController
public class SceneController {

    private ISceneService sceneService;

    @Autowired
    public void setSceneService(ISceneService sceneService) {
        this.sceneService = sceneService;
    }

    @ApiOperation("toggle scene's active status")
    @PutMapping("/scene/active/{uuid}")
    public ResponseResult<String> toggleActive(
            @PathVariable("uuid") String uuid
    ) {
        if (!StringUtils.hasLength(uuid)) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        sceneService.toggleActive(uuid);
        return ResponseResult.success("Toggle active succeeded!");
    }

    @ApiOperation("add new scene")
    @PostMapping("/scene")
    public ResponseResult<String> addScene(
            @RequestBody SceneAddParam sceneAddParam
    ) {
        if (sceneAddParam == null) {
            Asserts.fail(ResultCode.VALIDATE_FAILED);
        }

        sceneService.addScene(sceneAddParam);
        return ResponseResult.success("Add scene succeeded!");
    }
}
