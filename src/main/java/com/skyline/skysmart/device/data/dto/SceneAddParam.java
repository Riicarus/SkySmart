package com.skyline.skysmart.device.data.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * scene add param
 *
 * @author Skyline
 * @create 2022/6/22 20:26
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("SceneAddParam")
public class SceneAddParam {

    @ApiModelProperty("unique id of user")
    private String uid;

    @ApiModelProperty("scene's name")
    private String name;

    @ApiModelProperty("json type of instructions, raw type: HashMap<String, Queue<InstructionUnit>>")
    private JSONObject instructionsJson;
}
