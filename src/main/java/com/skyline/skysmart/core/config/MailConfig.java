package com.skyline.skysmart.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <功能概述><br/>
 * mail config
 *
 * @author 何锦川
 * @create 2022/5/13 17:50
 * @since 1.0.0
 */
@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    private String username;

}
