package org.spring.springboot.result;

/**
 * 返回体
 * @author coderpwh
 * @Date: 2018/5/4.
 * @Description:
 */
public class ResultBody {

    /**
     * 响应码
     */
    private  String  code;

    /**
     *  响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public ResultBody(Object result){

    }





    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


}
