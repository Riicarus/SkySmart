package com.skyline.skysmart.device.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * Device's Internet information
 *
 * @author Skyline
 * @create 2022/6/19 11:54
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInternetInfo {

    private String deviceId;

    private String IP;

    private String port;

    private String MAC;

}
