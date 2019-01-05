package com.liam.learn.scheduleExector;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ScheduledTask implements Runnable {

  private String name;

  private Random random;

  public ScheduledTask(String name) {
    this.name = name;
  }

  public ScheduledTask(String name, Random random) {
    this.name = name;
    this.random = random;
  }

  @Override
  public void run() {
    try {
      System.out.println(name + " run at: " + new Date());
      int randomNum = random.nextInt(100);
      TimeUnit.MILLISECONDS.sleep(randomNum);
      System.out.println(name + " finished after sleep " + randomNum + " ms");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
