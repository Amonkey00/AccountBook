package com.example.accountbook.utils;

public class JsonResult<T> {
    private T data;
    private long code;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonResult(){
        this.code = 200;
        this.message = "Operation Success";
    }

    public JsonResult(T data) {
        this.code = 200;
        this.data = data;
        this.message = "Opertaion Success";
    }

    public JsonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult(T data, String message) {
        this.code = 200;
        this.data = data;
        this.message = message;
    }

    public JsonResult(T data, long code, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

}
