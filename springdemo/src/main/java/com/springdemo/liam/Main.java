package com.springdemo.liam;

import com.springdemo.liam.component.ProtoComponent;
import com.springdemo.liam.service.FirstSubService;
import com.springdemo.liam.service.SecondService;
import com.springdemo.liam.service.SimpleTestService;
import org.bouncycastle.util.test.SimpleTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liam on 2016/12/19.
 */
public class Main {

  public static void main(String[] args) {
//    for (int i = 0; i < 10000; i++) {
      testOrderingOfBean();
//    }
  }

  public static void testOrderingOfBean() {
    ClassPathXmlApplicationContext applicationContext =
      new ClassPathXmlApplicationContext("/spring/single-bean-context-config.xml");
    SecondService service = applicationContext.getBean(SecondService.class);
    service.doSth();
    System.out.println("====================================================");
    applicationContext.close();
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
    FirstSubService service = applicationContext.getBean(FirstSubService.class);
    service.test();
  }


  public static void testInheritPrivateResource() {
    ClassPathXmlApplicationContext applicationContext =
      new ClassPathXmlApplicationContext("/spring/context-config.xml");

    SimpleTestService simpleTestService = applicationContext.getBean(SimpleTestService.class);
    simpleTestService.test();

  }
}
