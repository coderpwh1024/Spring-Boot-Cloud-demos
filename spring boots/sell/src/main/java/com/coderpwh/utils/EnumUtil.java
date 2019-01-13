package com.coderpwh.utils;

import com.coderpwh.enums.CodeEnum;

/**
 * @author coderpwh
 * @create 2019-01-13 22:23
 * @desc ${DESCRIPTION}
 **/
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
