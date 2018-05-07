package org.spring.springboot.constant;

import org.spring.springboot.result.ErrorInfoInterface;

/**
 * @author coderpwh
 * @Date: 2018/5/4.
 * @Description:
 */
public enum CityErrorInfoEnum implements ErrorInfoInterface {


    PARAMS_NO_COMPLETE("000001","params no complete"),
    CITY_EXIT("000002","city exit");

    private String code;

    private String message;

    CityErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
