package com.springdemo.liam.controller;

import com.springdemo.liam.util.LoggerSupport;
import com.springdemo.liam.vo.BaseParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liam on 2016/11/26.
 */
@Controller
@RequestMapping("/spring/demo")
public class BaseController extends LoggerSupport {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        String test = (String) request.getAttribute("test");
        logger.info("the test:{}", test);
        return "hello!";
    }

    @RequestMapping("/test/requestParam")
    @ResponseBody
    public String testRequestParam(@RequestParam(name = "name") String name, HttpServletRequest request) {
        String test = (String) request.getAttribute("test");
        logger.info("the test:{}", test);
        return "hello " + name;
    }

    @RequestMapping("/test/model")
    @ResponseBody
    public Object testModelParam(BaseParam param) {
        return param;
    }
}
