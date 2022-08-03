package com.skyline.skysmart.device.entity.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * user and device relation dao
 *
 * @author Skyline
 * @create 2022/7/26 16:23
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_device_relation")
public class UserDeviceRelationDAO {

    @TableId
    private String id;

    @TableField("uid")
    private String uid;

    @TableField("device_id")
    private String deviceId;

    @TableField("alias_name")
    private String aliasName;

    // json type of preset HashMap, each preset is a hashmap of properties' name and value
    // like: HashMap<String, HashMap<String, String>>, presetName, propertyId, propertyValue
    @TableField("presets")
    private String presets;

    // json type of properties HashMap, contains all properties the device has, always follows current preset
    // like: HashMap<String, IProperty>
    @TableField("properties")
    private String properties;

    @TableField("current_preset_name")
    private String currentPresetName;

}
