package com.skyline.skysmart.device.entity.converter;

import com.skyline.skysmart.device.entity.bo.IProductBO;
import com.skyline.skysmart.device.entity.bo.impl.ProductBO;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import org.springframework.stereotype.Component;

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

}
