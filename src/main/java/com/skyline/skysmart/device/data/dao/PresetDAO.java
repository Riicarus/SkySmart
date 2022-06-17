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
 * PresetDAO
 *
 * @author Skyline
 * @create 2022/6/17 0:16
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Preset DAO")
@TableName("preset")
public class PresetDAO {

    @TableId("uuid")
    @ApiModelProperty("unique id of preset")
    private String uuid;

    @TableField("name")
    @ApiModelProperty("name of properties")
    private String name;

    @TableField("properties")
    @ApiModelProperty("properties map json")
    private String properties;

}
