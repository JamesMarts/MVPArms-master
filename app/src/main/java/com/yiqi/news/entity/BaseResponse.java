package com.yiqi.news.entity;

import com.yiqi.news.mvp.model.api.Api;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private T data;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (code == Api.RequestSuccess) {
            return true;
        } else {
            return false;
        }
    }

}