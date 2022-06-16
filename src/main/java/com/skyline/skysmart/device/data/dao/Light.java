package com.skyline.skysmart.device.data.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * light dao
 *
 * @author Skyline
 * @create 2022/6/11 18:58
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "com.skyline.skysmart.device.data.dao.ILightBO", description = "light dao")
@TableName(value = "light")
public class Light {

    @TableId(value = "device_id")
    @ApiModelProperty(value = "unique id of device")
    private String deviceId;

    @TableField(value = "uid")
    @ApiModelProperty(value = "unique id of user")
    private String uid;

    @TableField(value = "name")
    @ApiModelProperty(value = "name")
    private String name;

    @TableField(value = "model_id")
    @ApiModelProperty(value = "model_id")
    private String modelId;

    @TableField(value = "properties")
    @ApiModelProperty(value = "propertiesJson")
    private String propertiesJson;

}
