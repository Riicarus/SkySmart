package com.skyline.skysmart.device.service;

import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import com.skyline.skysmart.device.entity.dto.ProductAddParam;

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

    void addProduct(ProductAddParam productAddParam);

}
