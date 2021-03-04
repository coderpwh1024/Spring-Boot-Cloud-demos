package com.codderpwh.kafka.util;

import java.io.Serializable;


/**
 * 响应结果类
 */
public class ResponseResult<T> implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -8525509680508090517L;

    /**
     * 默认操作成功时的错误码(0)
     */
    private static final int DEF_ERRCODE_SUCCESS = 200;

    /**
     * 默认操作成功时的返回消息
     */
    private static final String DEF_ERRMSG_SUCCESS = "操作成功";

    private int code;

    private String msg;

    private boolean succ;

    private String oper;

    public T data;

    public ResponseResult() {
    }

 /*   public ResponseResult(int errCode, String errMsg, T data) {
        this.code = errCode;
        this.msg = errMsg;
        this.data = data;
    }*/

    public ResponseResult(int errCode, String errMsg, T data, boolean succ, String oper ) {
        this.code = errCode;
        this.msg = errMsg;
        this.data = data;
        this.succ = succ;
        this.oper = oper;
    }

    /**
     * @param errCode 错误码，操作成功:0
     * @param errMsg  错误消息
     * @param data    要响应的数据
     * @return ResponseResult<T>
     * @Description: 用于构建响应结果的静态方法
     */
    public static <T> ResponseResult<T> build(int errCode, String errMsg, T data) {
        return new ResponseResult<T>(errCode, errMsg, data,true,"");
    }

    /**
     * @param @param errMsg 错误消息
     * @param @param data 要响应的数据
     * @return ResponseResult<T>
     * @Description: 用于构建成功响应结果的静态方法
     */
    public static <T> ResponseResult<T> buildSuccessResponse(String errMsg, T data) {
        return new ResponseResult<T>(DEF_ERRCODE_SUCCESS, errMsg, data,true,"");
    }

    /**
     * @param @param data 要响应的数据
     * @return ResponseResult<T>
     * @Description: 用于构建成功响应结果的静态方法
     */
    public static <T> ResponseResult<T> buildSuccessResponse(T data) {
        return buildSuccessResponse(DEF_ERRMSG_SUCCESS, data);
    }

    /**
     * @return ResponseResult<T>
     * @Description: 用于构建成功响应结果的静态方法
     */
    public static <T> ResponseResult<T> buildSuccessResponse() {
        return buildSuccessResponse(DEF_ERRMSG_SUCCESS, null);
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static int getDefErrcodeSuccess() {
        return DEF_ERRCODE_SUCCESS;
    }

    public static String getDefErrmsgSuccess() {
        return DEF_ERRMSG_SUCCESS;
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

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return boolean    成功:true,失败:false
     * @Description: 判断响应结果的状态
     */
  /*  public boolean isSuccess() {
        return this != null && this.getCode() == 200;
    }*/
}
