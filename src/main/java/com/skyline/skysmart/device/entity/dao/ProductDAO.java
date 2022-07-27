package com.skyline.skysmart.device.entity.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * product dao
 *
 * @author Skyline
 * @create 2022/7/26 16:21
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class ProductDAO {

    @TableId
    private String id;

    @TableField("name")
    private String name;

    @TableField("type")
    private String type;

    @TableField("produced_time")
    private Long producedTime;

    // json type of properties arraylist, default value of this type
    @TableField("default_properties")
    private String defaultProperties;

}
