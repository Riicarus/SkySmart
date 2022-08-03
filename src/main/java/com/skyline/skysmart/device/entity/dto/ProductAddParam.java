package com.skyline.skysmart.device.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * product add param
 *
 * @author Skyline
 * @create 2022-8-3 12:01
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddParam {

    private String name;

    private String type;

    private Long producedTime;

    private String defaultProperties;

}
