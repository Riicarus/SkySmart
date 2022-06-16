package com.skyline.skysmart.device.data.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * DeviceUserRelationDAO
 *
 * @author Skyline
 * @create 2022/6/17 0:13
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("DeviceUserRelation DAO")
@TableName(value = "device_user_relation")
public class DeviceUserRelationDAO {

    @TableId("uuid")
    @ApiModelProperty("unique id of relation")
    private String uuid;

    @TableField("uid")
    @ApiModelProperty("unique id of user")
    private String uid;

    @TableField("device_id")
    @ApiModelProperty("unique id of device")
    private String deviceId;

    @TableField("device_name")
    @ApiModelProperty("name of device")
    private String deviceName;

    @TableField("preset_id")
    @ApiModelProperty("unique id of preset")
    private String presetId;

}
