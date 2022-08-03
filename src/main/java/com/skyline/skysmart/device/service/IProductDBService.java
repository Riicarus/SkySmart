package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import com.skyline.skysmart.device.entity.dto.ProductAddParam;
import org.apache.ibatis.annotations.Param;

/**
 * [FEATURE INFO]<br/>
 * product db service interface
 *
 * @author Skyline
 * @create 2022/7/26 23:15
 * @since 1.0.0
 */
public interface IProductDBService {

    ProductDAO getProductDAO(String productId);

    IProductBO getProductBO(String productId);

    void addProduct(@Param("productAddParam") ProductAddParam productAddParam);

}
