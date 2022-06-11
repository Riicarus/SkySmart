package com.skyline.skysmart.core.enums;

import com.skyline.skysmart.core.response.IErrorCode;

/**
 * [FEATURE INFO]<br/>
 * ResultCode enums
 *
 * @author Skyline
 * @create 2022/6/10 16:06
 * @since 1.0.0
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(600, "操作成功"),
    NO_PERMISSION(601, "没有权限"),
    FAILED(602, "操作失败"),
    ELEMENT_EXISTS(603, "对应元素已存在"),
    NO_ELEMENT(604, "没有对应元素"),
    VALIDATE_FAILED(605, "参数校验失败");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
