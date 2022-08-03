package com.skyline.skysmart.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * [FEATURE INFO]<br/>
 * mqtt config
 *
 * @author Skyline
 * @create 2022-8-1 19:37
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties("spring.mqtt")
public class MqttConfig {

    private String host;

    private String clientId;

    private String username;

    private String password;

    private String topic;

    private String timeout;

    private String keepalive;

}
