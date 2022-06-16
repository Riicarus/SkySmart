package com.skyline.skysmart.auth.enums;

/**
 * [FEATURE INFO]<br/>
 * Login type of account
 *
 * @author Skyline
 * @create 2022/6/12 17:35
 * @since 1.0.0
 */
public enum LoginType {
    USER("user");
    private final String type;

    LoginType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
