package com.liam.learn.blockintqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-03-18.
 */
public class BlockingQueueDemo {

  public static void main(String[] args) {
    testTake();
  }

  public static void testTake() {
    BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(10);

    try {
      System.out.println(System.currentTimeMillis());
      Integer poll = blockingQueue.poll(2, TimeUnit.SECONDS);
      System.out.println(System.currentTimeMillis());
      System.out.println(poll);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void testPut() {
    BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(10);

    while (true) {
      try {
        int i = ThreadLocalRandom.current().nextInt(10000);
        blockingQueue.put(i);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
