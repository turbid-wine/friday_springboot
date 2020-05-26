package com.friday.base.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果类
 * @param
 */
@Data
public class Result implements Serializable {

    // 代码信息
    private Integer code;
    // 返回信息
    private String msg;
    // 数据数量
    private Integer count;
    // 数据
    private Object data;

    public Result(){

    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static Result getResult(Integer code, String msg){
        return  new Result(code,msg);
    }

    public static Result getResult(Integer code, String msg, Object data){
        return  new Result(code,msg,data);
    }
    public static Result getResult(Integer code, String msg,Integer count, Object data){
        return  new Result(code,msg,count,data);
    }

    // 无数据返回 成功
    public static Result success(){
        return new Result(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg());
    }

    // 无数据返回 失败
    public static Result failure(){
        return new Result(ResCode.FAIL.getCode(),ResCode.FAIL.getMsg());
    }

    // 有数据返回 成功
    public static Result success(Object data){
        return new Result(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg(),data);
    }

}
