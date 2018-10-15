package org.spring.springboot.result;

/**
 * @author coderpwh
 * @Date: 2018/5/4.
 * @Description:
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface {

    SUCCESS("0", "success"),
    NOT_FOUND("-1", "service not found");

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

}
