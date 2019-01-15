package com.coderpwh.exception;

import com.coderpwh.enums.ResultEnum;

/**
 * @author coderpwh
 * @create 2019-01-15 23:04
 * @desc ${DESCRIPTION}
 **/
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
