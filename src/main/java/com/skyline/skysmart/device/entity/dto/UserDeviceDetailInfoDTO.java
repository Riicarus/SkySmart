package com.skyline.skysmart.device.entity.dto;

import com.skyline.skysmart.device.entity.model.IProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    private String productId;

    private String aliasName;

    private Boolean networkStatus;

    private String currentPresetName;

    private HashMap<String, ArrayList<IProperty>> presets;

}
