package com.skyline.skysmart.device.entity.dto;

import com.skyline.skysmart.device.entity.model.IProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device user detail info dto
 *
 * @author Skyline
 * @create 2022-8-3 10:38
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceDetailInfoDTO {

    private String uid;

    private String deviceId;

    private String deviceName;

    private String aliasName;

    private String productId;

    private String productName;

    private String productType;

    private String ip;

    private String mac;

    private Boolean networkStatus;

    private String currentPresetName;

    private HashMap<String, HashMap<String, String>> presets;

    private HashMap<String, IProperty> properties;

}
