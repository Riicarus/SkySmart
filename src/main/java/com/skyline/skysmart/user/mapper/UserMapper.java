package com.skyline.skysmart.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skyline.skysmart.user.entity.dao.UserDAO;
import org.springframework.stereotype.Repository;

/**
 * [FEATURE INFO]<br/>
 * user mapper
 *
 * @author Skyline
 * @create 2022/6/12 17:09
 * @since 1.0.0
 */
@Repository
public interface UserMapper extends BaseMapper<UserDAO> {

}
