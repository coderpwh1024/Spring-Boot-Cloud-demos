package com.coderpwh.code.exeception;

import com.coderpwh.code.enums.ResultEnums;

/**
 * @author coderpwh
 * @Date: 2018/1/30.
 * @Description:
 */

public class GirlExeception extends RuntimeException {

    private Integer code;

    public GirlExeception(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code = resultEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
