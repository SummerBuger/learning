package com.springdemo.liam;

import com.springdemo.liam.service.SimpleService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by liam on 2016/12/19.
 */
public class Main {
  public static void main(String[] args) {
//    ClassPathXmlApplicationContext applicationContext =
//        new ClassPathXmlApplicationContext("/spring/context-config.xml");
//      SimpleService simpleService = applicationContext.getBean(SimpleService.class);
//      String hello = simpleService.hello();
//      System.out.printf(hello);


    for (int i = 0; i < 201; i++) {
      System.out.println(i + "def");
    }
  }
}
