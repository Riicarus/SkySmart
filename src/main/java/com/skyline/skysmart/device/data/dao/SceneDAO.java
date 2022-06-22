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
 * SceneDAO
 *
 * @author Skyline
 * @create 2022/6/22 13:34
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Scene DAO")
@TableName("scene")
public class SceneDAO {

    @TableId("uuid")
    @ApiModelProperty("unique id of scene")
    private String uuid;

    @TableField("uid")
    @ApiModelProperty("unique id of user")
    private String uid;

    @TableField("name")
    @ApiModelProperty("scene's name")
    private String name;

    @TableField("active")
    @ApiModelProperty("is scene in use now")
    private Boolean active;

    @TableField("instructions_json")
    @ApiModelProperty("json type of instructions, raw type: Queue<String")
    private String instructionsJson;

}
