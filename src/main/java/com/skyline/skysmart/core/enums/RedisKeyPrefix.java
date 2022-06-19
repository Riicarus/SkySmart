package com.skyline.skysmart.core.enums;

/**
 * [FEATURE INFO]<br/>
 * RedisKeyPrefix enums, easy using redis to store value
 *
 * @author Skyline
 * @create 2022/6/16 12:58
 * @since 1.0.0
 */
public enum RedisKeyPrefix {
    BIND_EMAIL_VERYCODE("BIND_EMAIL_VERYCODE", "EMAIL_BINDING_VERYCODE_"),
    SET_PASS_VERYCODE("SET_PASS_VERYCODE", "SET_PASS_VERYCODE_"),
    EMAIL_LOGIN_VERYCODE("EMAIL_LOGIN_VERYCODE", "EMAIL_LOGIN_VERYCODE_"),
    DEVICE_NET_INFO("DEVICE_NET_INFO", "DEVICE_NET_INFO_");

    private final String keyType;
    private final String keyPrefix;

    RedisKeyPrefix(String keyType, String keyPrefix) {
        this.keyType = keyType;
        this.keyPrefix = keyPrefix;
    }

    public String getKeyType() {
        return keyType;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }
}
