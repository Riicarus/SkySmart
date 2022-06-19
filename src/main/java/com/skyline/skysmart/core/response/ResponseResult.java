package com.skyline.skysmart.core.response;

import com.skyline.skysmart.core.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * [FEATURE INFO]<br/>
 * ResponseResult to front site
 *
 * @author Skyline
 * @create 2022/6/10 16:06
 * @since 1.0.0
 */
@ApiModel(value = "com.skyline.skysmart.core.response.ResponseResult", description = "服务器返回参数对象")
public class ResponseResult<T> {

    @ApiModelProperty(value = "请求处理状态")
    private int code;
    @ApiModelProperty(value = "请求处理结果信息")
    private String message;
    @ApiModelProperty(value = "请求返回数据对象")
    private T data;

    protected ResponseResult() {}

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> ResponseResult<T> success(T data, String message) {
        return new ResponseResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> ResponseResult<T> failed(IErrorCode errorCode) {
        return new ResponseResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> ResponseResult<T> failed(IErrorCode errorCode, String message) {
        return new ResponseResult<>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 错误信息
     */
    public static <T> ResponseResult<T> failed(String message) {
        return new ResponseResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResponseResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResponseResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResponseResult<T> validateFailed(String message) {
        return new ResponseResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 没有权限返回结果
     */
    public static <T> ResponseResult<T> noPermission() {
        return failed(ResultCode.NO_PERMISSION);
    }

    /**
     * 没有对应元素返回结果
     */
    public static <T> ResponseResult<T> noElement() {
        return failed(ResultCode.NO_ELEMENT);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
