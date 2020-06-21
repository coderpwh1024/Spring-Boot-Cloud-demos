package com.github.lly835.resp;

/**
 * 数据返回类
 * @author keywaytang
 *
 */
public class ResponseResult<T> extends BaseEntity{

	/** 
	* @Fields serialVersionUID
	*/ 
	private static final long serialVersionUID = -8525509680508090517L;

	/**
	 * 默认操作成功时的错误码(0)
	 */
	private static final int DEF_ERRCODE_SUCCESS = 0;

	/**
	 * 默认操作成功时的返回消息
	 */
	private static final String DEF_ERRMSG_SUCCESS = "操作成功";

	private int errCode;

	private String errMsg;

	public T data;

	public ResponseResult() {
	}

	public ResponseResult(int errCode, String errMsg, T data) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.data = data;
	}

	/**
	 * 
	 * @Description: 用于构建响应结果的静态方法
	 * @param  errCode 错误码，操作成功:0
	 * @param  errMsg 错误消息
	 * @param  data 要响应的数据
	 * @return ResponseResult<T>
	 */
	public static <T> ResponseResult<T> build(int errCode, String errMsg, T data) {
		return new ResponseResult<T>(errCode, errMsg, data);
	}

	/**
	 * 
	 * @Description: 用于构建成功响应结果的静态方法
	 * @param @param
	 *            errMsg 错误消息
	 * @param @param
	 *            data 要响应的数据
	 * @return ResponseResult<T>
	 */
	public static <T> ResponseResult<T> buildSuccessResponse(String errMsg, T data) {
		return new ResponseResult<T>(DEF_ERRCODE_SUCCESS, errMsg, data);
	}

	/**
	 * 
	 * @Description: 用于构建成功响应结果的静态方法
	 * @param @param
	 *            data 要响应的数据
	 * @return ResponseResult<T>
	 */
	public static <T> ResponseResult<T> buildSuccessResponse(T data) {
		return buildSuccessResponse(DEF_ERRMSG_SUCCESS, data);
	}

	/**
	 * 
	 * @Description: 用于构建成功响应结果的静态方法
	 * @return ResponseResult<T>
	 */
	public static <T> ResponseResult<T> buildSuccessResponse() {
		return buildSuccessResponse(DEF_ERRMSG_SUCCESS, null);
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 
	* @Description: 判断响应结果的状态
	* @return boolean	成功:true,失败:false
	 */
	public boolean isSuccess() {
		return this != null && this.getErrCode() == 0;
	}
}
