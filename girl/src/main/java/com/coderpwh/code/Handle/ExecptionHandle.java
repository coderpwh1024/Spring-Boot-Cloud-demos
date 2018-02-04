package com.coderpwh.code.Handle;

import com.coderpwh.code.domain.Result;
import com.coderpwh.code.exeception.GirlExeception;
import com.coderpwh.code.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author coderpwh
 * @Date: 2018/1/29.
 * @Description:
 */
@ControllerAdvice
public class ExecptionHandle {


    private final static Logger logger = LoggerFactory.getLogger(ExecptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {

        if (e instanceof GirlExeception) {
            GirlExeception girlExeception = (GirlExeception) e;
            return ResultUtil.error(((GirlExeception) e).getCode(), girlExeception.getMessage());
        } else {
            logger.info("系统异常{}", e);
            return ResultUtil.error(1, "未知错误!");
        }

    }

}
