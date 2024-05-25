package com.roc.admin.backend.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseData<T> {
    /**
     * 结果状态 ,具体状态码参见ResultData.java
     */
    private int status;
    private String message;
    private T data;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date time;


    public ResponseData() {
        this.time = new Date();
    }

    public static <T> ResponseData<T> success() {
        ResponseData<T> resultData = new ResponseData<>();
        resultData.setStatus(ResponseCode.SUCCESS.getCode());
        resultData.setMessage(ResponseCode.SUCCESS.getMessage());
        resultData.setData(null);
        return resultData;
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> resultData = new ResponseData<>();
        resultData.setStatus(ResponseCode.SUCCESS.getCode());
        resultData.setMessage(ResponseCode.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResponseData<T> fail(ResponseCode response) {
        ResponseData<T> resultData = new ResponseData<>();
        resultData.setStatus(response.getCode());
        resultData.setMessage(response.getMessage());
        return resultData;
    }

    public static <T> ResponseData<T> fail(T data) {
        ResponseData<T> resultData = new ResponseData<>();
        resultData.setStatus(ResponseCode.FAIL.getCode());
        resultData.setMessage(ResponseCode.FAIL.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResponseData<T> fail(int code, String message) {
        ResponseData<T> resultData = new ResponseData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }

}