package com.coderpwh.enums;

import lombok.Getter;

/**
 * @author coderpwh
 * @create 2019-01-22 22:13
 * @desc ${DESCRIPTION}
 **/
@Getter
public enum  PayStatusEnum implements CodeEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return null;
    }
}
