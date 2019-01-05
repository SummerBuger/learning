package com.base.liam.signaldemo;

import sun.misc.Signal;

import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-05-24.
 */
public class SignalDemo {

  public static void main(String[] args) {
    DemoHandler demoHandler = new DemoHandler();
    Signal.handle(new Signal("KILL"), demoHandler);
    Signal.handle(new Signal("TERM"), demoHandler);

    while (true) {
      try {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("I am alive without signal");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
