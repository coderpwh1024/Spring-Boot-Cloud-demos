package com.coderpwh.Vo;

import lombok.Data;

/**
 * @author coderpwh
 * @create 2019-01-21 22:59
 * @desc ${DESCRIPTION}
 **/
@Data
public class ResultVO<T> {
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
