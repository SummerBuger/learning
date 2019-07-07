package com.springdemo.liam.service.impl;

import com.springdemo.liam.service.SecondService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class SecondServiceImpl implements SecondService {

  public SecondServiceImpl() {
    System.out.println("The constructor of second service");
  }

  @PostConstruct
  public void init() {
    System.out.println("init class " + this.getClass().getName());
  }

  @Override
  public void doSth() {
    System.out.println("second service call do something");
  }
}
