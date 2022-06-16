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
 * Device DAO
 *
 * @author Skyline
 * @create 2022/6/17 0:05
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Device DAO")
@TableName(value = "device", autoResultMap = true)
public class DeviceDAO {

    @TableId("uuid")
    @ApiModelProperty("unique id of device")
    private String uuid;

    @TableField("type")
    @ApiModelProperty("type of device")
    private String type;

    @TableField("model_name")
    @ApiModelProperty("model of device")
    private String modelName;

    @TableField(value = "property_names", typeHandler = FastjsonTypeHandler.class)
    @ApiModelProperty("property names of this model of device")
    private String[] propertyNames;

}
