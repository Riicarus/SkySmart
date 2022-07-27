package com.skyline.skysmart.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [FEATURE INFO]<br/>
 * user login dto
 *
 * @author Skyline
 * @create 2022/6/12 17:52
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    private String uid;

    private String username;

    private String email;

    private String token;

}
