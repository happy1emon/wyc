package com.xg.internalcommon.dto;


import com.xg.internalcommon.constant.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
//每set一个code 返回整个对象
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;
    /*
    成功响应的方法
     */



    public static <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }
    /**
     * 成功响应的方法
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }
    //多种失败方法
    /**
     * 统一失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }
    /**
     * 失败：自定义失败 错误码和提示信息
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }
    /**
     * 失败：自定义失败 错误码、提示信息、数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static  ResponseResult fail(int code,String message,String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }






}
