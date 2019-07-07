package com.springdemo.liam.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleTestService extends AbstractTestService {

  public void test() {
    System.out.println("start to invoke do test");
    doTest();
    System.out.println("stop to invoke do test");
  }


}
