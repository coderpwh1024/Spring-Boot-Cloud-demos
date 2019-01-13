package com.coderpwh.enums;

import lombok.Getter;

/**
 * @author coderpwh
 * @create 2019-01-13 22:21
 * @desc ${DESCRIPTION}
 **/
@Getter
public enum ProductStatusEnum implements CodeEnum {

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
