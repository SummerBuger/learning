package com.springdemo.liam.service;

import org.springframework.stereotype.Service;

/**
 * Created by chaochun.ccc on 2017-05-24.
 */
@Service
public class SecondSubService implements MultiSubService {
  public void test() {
    System.out.println("second service");
  }
}
