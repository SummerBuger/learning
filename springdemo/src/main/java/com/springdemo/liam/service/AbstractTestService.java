package com.springdemo.liam.service;

import javax.annotation.Resource;

public abstract class AbstractTestService {

  @Resource
  private FirstSubService firstSubService;

  protected void doTest() {
    firstSubService.test();
  }

}
