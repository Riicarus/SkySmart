package com.skyline.skysmart.device.entity.vo;

import com.skyline.skysmart.device.entity.model.IProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [FEATURE INFO]<br/>
 * device info cached in redis
 *
 * @author Skyline
 * @create 2022/7/27 10:31
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCachedInfo {

    private String relationId;

    private String aliasName;

    private String deviceId;

    private String deviceName;

    private String uid;

    private String username;

    private String productId;

    private String productName;

    private String productType;

    private String ip;

    private String mac;

    private Long createTime;

    private Long lastRegisterTime;

    private HashMap<String, ArrayList<IProperty>> presets;

    private String currentPresetName;

}
