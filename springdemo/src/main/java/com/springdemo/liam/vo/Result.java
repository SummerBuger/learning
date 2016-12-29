package com.springdemo.liam.vo;

/**
 * Created by liam on 2016/12/21.
 */
public class Result<T> {

    private boolean ret;

    private String message;

    private T data;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
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

    public Result<T> withRet(boolean ret) {
        this.ret = ret;
        return this;
    }

    public Result<T> withData(T data) {
        this.data = data;
        return this;
    }
    public Result<T> withMsg(String msg) {
        this.message = msg;
        return this;
    }

    public static <T> Result<T> success(boolean ret) {
        return success(ret, null);
    }

    public static <T> Result<T> success(boolean ret, T t) {
        return new Result<T>().withRet(ret).withData(t);
    }

    public static <T> Result<T> fail() {
        return new Result<T>().withRet(false);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<T>().withRet(false).withMsg(message);
    }
}
