package com.skyline.skysmart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.skyline.skysmart.auth.mapper", "com.skyline.skysmart.device.mapper"})
public class SkySmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkySmartApplication.class, args);
    }

}
