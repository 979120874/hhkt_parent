package com.ik.hhkt.error;

import com.ik.hhkt.result.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @className: GlobalExceptionHandler
 * @author: weishihuan
 * @date: 2022-07-06 21:54
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(HttpServletRequest request, Exception e) {
        return Result.fail(e.getMessage());
    }

}
