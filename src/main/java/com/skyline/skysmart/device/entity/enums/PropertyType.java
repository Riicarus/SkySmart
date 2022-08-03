package com.skyline.skysmart.device.entity.enums;

/**
 * [FEATURE INFO]<br/>
 * property value type
 *
 * @author Skyline
 * @create 2022-8-3 16:36
 * @since 1.0.0
 */
public enum PropertyType {

    DEVICE_ONLY("DEVICE_ONLY"),
    ALL("ALL");

    private final String type;

    PropertyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
