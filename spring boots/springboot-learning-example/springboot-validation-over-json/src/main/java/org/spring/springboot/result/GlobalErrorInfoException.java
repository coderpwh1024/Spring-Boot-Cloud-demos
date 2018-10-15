package org.spring.springboot.result;

/**
 * @author coderpwh
 * @Date: 2018/5/4.
 * @Description:
 */
public class GlobalErrorInfoException  extends  Exception{

    private ErrorInfoInterface errorInfo;

    public GlobalErrorInfoException (ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ErrorInfoInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}
