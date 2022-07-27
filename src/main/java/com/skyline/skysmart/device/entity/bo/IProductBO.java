package com.skyline.skysmart.device.entity.bo;

import com.skyline.skysmart.device.entity.dao.ProductDAO;
import com.skyline.skysmart.device.entity.model.IProduct;

/**
 * [FEATURE INFO]<br/>
 * product bo
 *
 * @author Skyline
 * @create 2022/7/26 17:09
 * @since 1.0.0
 */
public interface IProductBO extends IProduct {

    void mapProductDAO(ProductDAO productDAO);

    ProductDAO getProductDAO();

    void assertProductDAONotNull();

}
