package com.base.liam.signaldemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-05-24.
 */
public class ShutdownHookDemo {

  public void forHook() {
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      public void run() {
        System.out.println("the shutdown hook has been executed");
      }
    }));
  }

  public static void main(String[] args) {
    new ShutdownHookDemo().forHook();
    System.out.println("the application start to working");

    while (true) {
      try {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("I am alive without signal");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
