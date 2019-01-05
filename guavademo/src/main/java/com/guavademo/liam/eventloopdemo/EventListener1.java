package com.guavademo.liam.eventloopdemo;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-05-21.
 */
public class EventListener1 {

  @Resource
  private EventBus eventBus;

  @PostConstruct
  public void register() {
    eventBus.register(this);
  }

  @Subscribe
  public void customer2(SimpleEvent simpleEvent) throws InterruptedException {
    System.out.println("【customer2】 get the simpleEvent and custom, event: " + simpleEvent);
    TimeUnit.SECONDS.sleep(1);
    System.out.println("【customer2】 finished do with the event: " + simpleEvent);
  }

  @Subscribe
  public void customer11(SimpleEvent simpleEvent) throws InterruptedException {
    System.out.println(" 【customer1】 get the simpleEvent and custom, event: " + simpleEvent);
    TimeUnit.SECONDS.sleep(1);
    System.out.println(" 【customer1】 finished do with the event: " + simpleEvent);
  }
}
