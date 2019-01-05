package com.guavademo.liam.eventloopdemo;

import com.google.common.eventbus.EventBus;

import java.util.Date;

/**
 * Created by chaochun.ccc on 2017-05-21.
 */
public class EventProducer {

  public static void main(String[] args) {
    EventBus eventBus = new EventBus("test-event-bus");
    SimpleEvent event = new SimpleEvent().setContent("this is a test event content").setName("test").setCreateTime(new Date());
    eventBus.register(new EventListener1());
    eventBus.post(event);
  }

}
