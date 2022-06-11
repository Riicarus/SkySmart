package com.skyline.skysmart.auth.enums;

/**
 * <功能概述>
 * <login type of account>
 *
 * @author 何锦川
 * @create 2022/4/20 16:39
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
