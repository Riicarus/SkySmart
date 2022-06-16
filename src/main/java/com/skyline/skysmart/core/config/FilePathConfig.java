package com.skyline.skysmart.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * [FEATURE INFO]<br/>
 * configuration of file path
 *
 * @author Skyline
 * @create 2022/6/12 17:22
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties("config.file.storepath")
public class FilePathConfig {

}
