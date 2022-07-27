package com.skyline.skysmart.device.service.impl;

import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.converter.ProductDataConverter;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import com.skyline.skysmart.device.mapper.ProductMapper;
import com.skyline.skysmart.device.service.IProductDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * [FEATURE INFO]<br/>
 * product db service
 *
 * @author Skyline
 * @create 2022/7/26 23:17
 * @since 1.0.0
 */
@Service
public class ProductDBService implements IProductDBService {

    private ProductMapper productMapper;

    private ProductDataConverter productDataConverter;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setProductDataConverter(ProductDataConverter productDataConverter) {
        this.productDataConverter = productDataConverter;
    }

    @Override
    public ProductDAO getProductDAO(String productId) {
        return productMapper.selectById(productId);
    }

    @Override
    public IProductBO getProductBO(String productId) {
        return productDataConverter.castToProductBO(getProductDAO(productId));
    }
}
