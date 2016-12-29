package com.springdemo.liam.controlleradvice;

import com.springdemo.liam.util.LoggerSupport;
import com.springdemo.liam.vo.BaseParam;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liam on 2016/12/19.
 */
@ControllerAdvice
public class BaseControllerAdvice extends LoggerSupport {

    @InitBinder
    public void init(HttpServletRequest request, WebDataBinder webDataBinder) {
        logger.info(webDataBinder.getFieldMarkerPrefix());
        logger.info("the init binder for: {}", request.getRequestURI());
    }

    @ModelAttribute
    public BaseParam buildParam(HttpServletRequest request) {
        logger.info("buildParam for: {}", request.getRequestURI());
        BaseParam baseParam = new BaseParam();
        baseParam.setId(1);
        return baseParam;
    }

    @ExceptionHandler
    @ResponseBody
    public Object handlerException(Exception e, HttpServletRequest request) {
        logger.info("exception: {}", e);
        return e;
    }
}
