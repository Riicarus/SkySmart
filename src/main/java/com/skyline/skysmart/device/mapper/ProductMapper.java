package com.skyline.skysmart.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skyline.skysmart.device.entity.dao.ProductDAO;
import org.springframework.stereotype.Repository;

/**
 * [FEATURE INFO]<br/>
 * product mapper
 *
 * @author Skyline
 * @create 2022/7/26 16:54
 * @since 1.0.0
 */
@Repository
public interface ProductMapper extends BaseMapper<ProductDAO> {
}
