package com.base.liam.inherit;

public abstract class AbstractTest {

  private SimpleService simpleService;

  public AbstractTest(SimpleService simpleService) {
    this.simpleService = simpleService;
  }

  public void invoke() {
    simpleService.doSth();
  }
}
