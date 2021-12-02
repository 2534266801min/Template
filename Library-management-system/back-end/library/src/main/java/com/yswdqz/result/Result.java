package com.yswdqz.result;


public class Result {
    private int code;
    private String message;
    private Object data;
    public Result(String message,int code){
        this.code = code;
        this.message = message;
    }
    public Result(String message,int code,Object o){
        this.code = code;
        this.message = message;
        this.data = o;
    }

}
