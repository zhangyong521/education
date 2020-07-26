package com.zhangyong.exception;

import com.zhangyong.utils.ResultMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-07-26 22:53
 * @PS: 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultMsg error(EducationException e) {
        e.printStackTrace();
        return ResultMsg.error().message(e.getMsg()).code(e.getCode());
    }

}
