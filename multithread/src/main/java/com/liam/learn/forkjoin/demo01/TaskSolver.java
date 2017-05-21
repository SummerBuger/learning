package com.liam.learn.forkjoin.demo01;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-04-26.
 */
public class TaskSolver {

  public static final int SLEEP_TIME = 3;

  public Integer solver() {
    int i = ThreadLocalRandom.current().nextInt(20);
    try {
      TimeUnit.SECONDS.sleep(SLEEP_TIME);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return i;
  }

}
