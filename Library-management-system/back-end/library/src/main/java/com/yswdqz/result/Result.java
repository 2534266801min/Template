package com.yswdqz.result;


public class Result {
    private int code = 200;
    private String message = "成功";
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
