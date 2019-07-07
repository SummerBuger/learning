package com.springdemo.liam.controller;

import com.springdemo.liam.util.LoggerSupport;
import com.springdemo.liam.vo.MapParam;
import com.springdemo.liam.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liam on 2016/11/26.
 */
@Controller
@RequestMapping("/spring/test")
public class BaseTestController extends LoggerSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestController.class);

  @RequestMapping(value = "/test/map/parameter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ResponseBody
  public Result<MapParam> testMapParam(MapParam mapParam) {
    return Result.success(true, mapParam);
  }

}
