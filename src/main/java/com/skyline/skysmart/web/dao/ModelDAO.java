package com.skyline.skysmart.web.dao;

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
 * model dao
 *
 * @author Skyline
 * @create 2022/6/11 19:15
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "com.skyline.skysmart.web.dao.ModelDAO", description = "model dao")
@TableName(value = "model")
public class ModelDAO {

    @TableId(value = "model_id")
    @ApiModelProperty(value = "unique id of model")
    private String modelId;

    @TableField(value = "model_name")
    @ApiModelProperty(value = "modelName")
    private String modelName;

    @TableField(value = "information")
    @ApiModelProperty(value = "information")
    private String information;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "createTime")
    private Long createTime;

}
