package com.springdemo.liam;

import com.springdemo.liam.component.ProtoComponent;
import com.springdemo.liam.service.FirstSubService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liam on 2016/12/19.
 */
public class Main {

  public static void main(String[] args) {
    testGetBean();
  }

  public static void testFirstService() {
    ClassPathXmlApplicationContext applicationContext =
      new ClassPathXmlApplicationContext("/spring/context-config.xml");
    FirstSubService service = applicationContext.getBean(FirstSubService.class);
    service.test();
  }

  public static void testGetBean() {
    ClassPathXmlApplicationContext applicationContext =
      new ClassPathXmlApplicationContext("/spring/context-config.xml");
    ProtoComponent simpleService = applicationContext.getBean(ProtoComponent.class);
    Long hello = simpleService.countVal();
    System.out.println("simpleService.countVal(): " + hello);
//
//    SimpleRestController simpleRestController = applicationContext.getBean(SimpleRestController.class);
//    Result<String> result = simpleRestController.testRest();
//    System.out.println(result);

    FirstSubService service = applicationContext.getBean(FirstSubService.class);
    service.test();
  }
}
