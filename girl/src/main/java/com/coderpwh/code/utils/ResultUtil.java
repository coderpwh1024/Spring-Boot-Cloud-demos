package com.coderpwh.code.utils;

import com.coderpwh.code.domain.Result;

/**
 * @author coderpwh
 * @Date: 2018/1/29.
 * @Description:
 */
public class ResultUtil {

    public static Result success(Object object) {

        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }


    public static Result error(Integer code, String msg) {

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
