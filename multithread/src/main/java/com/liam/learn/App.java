package com.liam.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println("sub thread sleep 2s");
          TimeUnit.SECONDS.sleep(2L);
          System.out.println("sub thread sleep 2s over");

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    System.out.println(executorService.isTerminated());

    executorService.shutdown();
    while (!executorService.isTerminated());
    System.out.println("Over!");
  }
}
