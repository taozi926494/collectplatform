package com.collectplatform.core.common;

import java.io.Serializable;

public class R<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public static final R<String> SUCCESS = new R<String>(null);
    public static final R<String> FAIL = new R<String>(FAIL_CODE, null);

    private int code;
    private String msg;
    private T data;

    public R(){}
    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public R(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void getData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "com.collectplatform.core.common.R [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
}