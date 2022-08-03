package com.skyline.skysmart.user.entity.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user")
public class UserDAO {

    @TableId("uid")
    private String uid;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("salt")
    private String salt;

    @TableField("admin")
    private Boolean admin;

}
