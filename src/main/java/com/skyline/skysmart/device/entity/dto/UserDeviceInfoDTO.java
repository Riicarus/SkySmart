package com.skyline.skysmart.device.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * device info of user
 *
 * @author Skyline
 * @create 2022/7/27 15:46
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceInfoDTO {

    private String uid;

    private String deviceId;

    private String productId;

    private String aliasName;

    private String currentPresetName;

}
