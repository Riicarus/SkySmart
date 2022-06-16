package com.skyline.skysmart.core.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * [FEATURE INFO]<br/>
 * add global cross support
 *
 * @author Skyline
 * @create 2022/6/12 14:43
 * @since 1.0.0
 */
@Configuration
@Data
public class GlobalCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
