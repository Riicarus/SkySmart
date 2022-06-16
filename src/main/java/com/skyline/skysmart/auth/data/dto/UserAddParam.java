package com.skyline.skysmart.auth.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * UserAddParam
 *
 * @author Skyline
 * @create 2022/6/12 18:05
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddParam {

    private String username;

    private String password;

}
