package com.skyline.skysmart.device.controller;

import com.skyline.skysmart.core.response.ResponseResult;
import com.skyline.skysmart.device.entity.dto.ProductAddParam;
import com.skyline.skysmart.device.service.IProductDBService;
import com.skyline.skysmart.user.shiro.AssertPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * [FEATURE INFO]<br/>
 * product controller
 *
 * @author Skyline
 * @create 2022-8-3 11:30
 * @since 1.0.0
 */
@Api
@RestController
public class ProductController {

    private IProductDBService productDBService;

    @Autowired
    public void setProductDBService(IProductDBService productDBService) {
        this.productDBService = productDBService;
    }

    @PostMapping("/product")
    @ApiOperation("add product")
    public ResponseResult<Boolean> addProduct(
            @RequestBody ProductAddParam productAddParam
    ) {
        AssertPermission.single("admin:*:*");

        productDBService.addProduct(productAddParam);

        return ResponseResult.success(true);
    }
}
