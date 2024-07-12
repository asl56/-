package com.Tzj.lost_found_system.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private Object data;
    private Object data2;

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){
        return new Result(1,"sucesss",null);
    }

    public static Result success(Object data){
        return new Result(1,"sucesss",data);
    }

    public static Result error(String msg){
        return new Result(0,msg,null);
    }

    public static Result successAndObject(Object data, Object data2) {
        return new Result(1,"sucesss",data,data2);
    }
}
