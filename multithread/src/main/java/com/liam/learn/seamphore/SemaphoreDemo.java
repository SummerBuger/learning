package com.liam.learn.seamphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-03-18.
 */
public class SemaphoreDemo {

  public static void main(String[] args) {
    waitSubThreadFinish();
  }

  public static void waitSubThreadFinish() {
    Semaphore semaphore = new Semaphore(1);
    new SimpleTask(semaphore).run();
    try {
      System.out.println("主线程好像开始等待了！");
      System.out.println("等待之前是不是先干点什么？？？？");
      semaphore.acquire();
      System.out.println("主线程等到了！");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static class SimpleTask implements Runnable {

    private Semaphore semaphore;

    public SimpleTask(Semaphore semaphore) {
      this.semaphore = semaphore;
    }

    @Override
    public void run() {
      try {
        System.out.println("子线程开始了！");
        semaphore.acquire();
        TimeUnit.SECONDS.sleep(10);
        semaphore.release();
        System.out.println("子线程释放了锁！");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
