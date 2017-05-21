package com.liam.learn.daemonthread;

import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-03-29.
 */
public class DaemonThreadDemo {

  public static void main(String[] args) {
    Thread daemonThread = new Thread(new DaemonThread());
    daemonThread.setDaemon(true);
    System.out.println(System.currentTimeMillis());
    daemonThread.start();
  }

  static class DaemonThread implements Runnable {

    @Override
    public void run() {
      try {
        System.out.println("我是一个守护线程");
        System.out.println(System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("睡眠两秒结束！");
      } catch (Exception e) {
        System.out.println("我这里报错了！！！！");
      } finally {
        System.out.println("我是不会被执行的 finally");
      }
    }
  }
}
