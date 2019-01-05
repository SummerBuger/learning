package com.liam.learn.threadcontext;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        ThreadContext.init();
        ThreadContext.put("main-sub-thread", "main-sub-methread");
        test();

        System.out.println(ThreadContext.get("hehe"));
        ThreadContext.clean();
      }
    }).start();
  }

  public static void test() {
    Executors.newSingleThreadExecutor().execute(new Runnable() {
      @Override
      public void run() {
        Object testParam = ThreadContext.get("main-sub-thread");
        System.out.println(testParam);
        int random = ThreadLocalRandom.current().nextInt();
        ThreadContext.init();
        ThreadContext.put("hehe", random);
      }
    });
  }

}
