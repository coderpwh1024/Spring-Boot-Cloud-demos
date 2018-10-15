package com.imooc.exception;

import com.imooc.pojo.IMoocJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author coderpwh
 * @Date: 2018/4/10.
 * @Description:
 */
@RestControllerAdvice
public class IMoocAjaxExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public IMoocJSONResult defaultErrorHandler(HttpServletRequest req,
                                               Exception e) throws Exception {
        e.printStackTrace();
        return IMoocJSONResult.errorException(e.getMessage());
    }

}
