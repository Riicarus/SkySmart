package com.skyline.skysmart.device.entity.converter;

import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.bo.impl.ProductBO;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import com.skyline.skysmart.device.entity.dto.ProductAddParam;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * [FEATURE INFO]<br/>
 * product data converter
 *
 * @author Skyline
 * @create 2022/7/26 23:10
 * @since 1.0.0
 */
@Component
public class ProductDataConverter {

    public IProductBO castToProductBO(ProductDAO productDAO) {
        if (productDAO == null) {
            return new ProductBO();
        }

        IProductBO productBO = new ProductBO();
        productBO.mapProductDAO(productDAO);

        return productBO;
    }

    public ProductDAO castToProductDAO(ProductAddParam productAddParam) {
        ProductDAO productDAO = new ProductDAO();

        String uuid = UUID.randomUUID().toString();
        productDAO.setId(uuid);
        productDAO.setName(productAddParam.getName());
        productDAO.setType(productAddParam.getType());
        productDAO.setProducedTime(productAddParam.getProducedTime());
        productDAO.setDefaultProperties(productAddParam.getDefaultProperties());

        return productDAO;
    }

}
