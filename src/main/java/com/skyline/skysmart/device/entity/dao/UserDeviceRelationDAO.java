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

    // json type of preset HashMap, each preset is a list of properties
    // like: HashMap<String, ArrayList<IProperty>>
    @TableField("presets")
    private String presets;

}
