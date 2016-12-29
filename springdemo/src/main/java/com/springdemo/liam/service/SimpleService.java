package com.springdemo.liam.service;

import org.springframework.stereotype.Service;

/**
 * Created by liam on 2016/12/19.
 */
@Service
public class SimpleService {

    public String hello() {
        System.out.println("the hello() method of Simple Service");
        return "hello!";
    }

}
