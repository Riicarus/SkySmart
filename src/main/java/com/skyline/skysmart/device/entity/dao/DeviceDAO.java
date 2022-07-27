package com.skyline.skysmart.device.entity.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * device dao
 *
 * @author Skyline
 * @create 2022/7/26 16:19
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("device")
public class DeviceDAO {

    @TableId
    private String id;

    @TableField("name")
    private String name;

    @TableField("product_id")
    private String productId;

    @TableField("create_time")
    private Long createTime;

    @TableField("last_register_time")
    private Long lastRegisterTime;

}
