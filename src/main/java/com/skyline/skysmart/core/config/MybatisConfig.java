package com.skyline.skysmart.core.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [FEATURE INFO]<br/>
 * mybatis plus configuration
 *
 * @author Skyline
 * @create 2022/6/12 14:43
 * @since 1.0.0
 */
@Configuration
public class MybatisConfig {

    // positive lock
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        // max data number of one page
        paginationInterceptor.setLimit(200);

        return paginationInterceptor;
    }

}
