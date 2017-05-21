package com.base.liam.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liam on 2017-03-05.
 */
public class WaitForThreadPoolDemo {

  private static ThreadPoolExecutor executor =
      new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      executor.execute(new Task("task" + i));
    }
    executor.shutdown();
    while (!executor.isTerminated());
    System.out.println("Finished!!!");
  }

}


class Task implements Runnable{

  private String name;

  public Task(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    try {
      TimeUnit.SECONDS.sleep(10l);
      System.out.println("the task: " + name + " finished");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}