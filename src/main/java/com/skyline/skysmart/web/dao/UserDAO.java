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
 * user dao
 *
 * @author Skyline
 * @create 2022/6/11 18:42
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "com.skyline.skysmart.web.dao.UserDAO", description = "user dao")
@TableName(value = "user")
public class UserDAO {

    @TableId(value = "uid")
    @ApiModelProperty(value = "unique id of user")
    private String uid;

    @TableField(value = "username")
    @ApiModelProperty(value = "username")
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(value = "password")
    private String password;

    @TableField(value = "email")
    @ApiModelProperty(value = "email")
    private String email;

    @TableField(value = "salt")
    @ApiModelProperty(value = "encryption salt")
    private String salt;

}
