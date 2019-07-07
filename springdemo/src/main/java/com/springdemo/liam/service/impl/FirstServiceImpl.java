package com.springdemo.liam.service.impl;

import com.springdemo.liam.service.FirstService;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("secondServiceImpl")
public class FirstServiceImpl implements FirstService {

  private boolean init = false;

  public FirstServiceImpl() {
    System.out.println("The constructor of first service");
  }

  @PostConstruct
  public void init() {
    System.out.println("init class " + this.getClass().getName());
    doInit();
  }

  @Override
  public void doSth() {
    System.out.println("first service do something......");
  }

  @Override
  public boolean hasInited() {
    return init;
  }

  private void doInit() {
    init = true;
  }
}
