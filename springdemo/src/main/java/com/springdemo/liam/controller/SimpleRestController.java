package com.springdemo.liam.controller;

import com.springdemo.liam.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liam on 2016/12/20.
 */
@RestController
public class SimpleRestController {

    @RequestMapping("/test/rest")
    public Result<String> testRest() {
        return Result.success(true, "hello");
    }
}
