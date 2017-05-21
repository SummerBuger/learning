package com.springdemo.liam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springdemo.liam.component.ProtoComponent;
import com.springdemo.liam.util.LoggerSupport;
import com.springdemo.liam.vo.BaseParam;
import com.springdemo.liam.vo.SubParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by liam on 2016/11/26.
 */
@Controller
@RequestMapping("/spring/demo")
public class BaseController extends LoggerSupport {

    @Value("#{'${test.list}'.split(',')}")
    private List<String> testList;

    @Resource
    private ProtoComponent protoComponent;

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(HttpServletRequest request) {
        String test = (String) request.getAttribute("test");
        Long val = protoComponent.countVal();
        logger.info("the test:{} val:{}", test, val);
        return testList;
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

    public static void main(String[] args) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(new BaseParam()));
        System.out.println(objectMapper.writeValueAsString(new SubParam()));


        String url = "subParam={\"winfoOrIdeaEmpty\":false,\"filterMapJson\":{},\"uid\":\"66755\",\"parentLevel\":1,\"startDate\":\"2017-04-27\",\"endDate\":\"2017-05-03\",\"reqPageIndex\":1,\"recordPerPage\":20,\"onlyBatchIdList\":false,\"styleType\":0}";

        String encode = URLEncoder.encode(url, StandardCharsets.UTF_8.displayName());
        System.out.println(encode);

    }
}
