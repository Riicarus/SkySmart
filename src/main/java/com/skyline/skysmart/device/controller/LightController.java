package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.device.mapper.LightMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * [FEATURE INFO]<br/>
 * light control object
 *
 * @author Skyline
 * @create 2022/6/10 15:46
 * @since 1.0.0
 */
@RestController
@Api
public class LightController {

    private LightMapper lightMapper;

    @Autowired
    public void setLightMapper(LightMapper lightMapper) {
        this.lightMapper = lightMapper;
    }
}
