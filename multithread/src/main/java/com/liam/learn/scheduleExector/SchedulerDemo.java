package com.liam.learn.scheduleExector;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SchedulerDemo {

  private static ScheduledExecutorService manualSingleScheduler = Executors
    .newScheduledThreadPool(1);

  private static ScheduledExecutorService defaultSingleScheduler = Executors
    .newSingleThreadScheduledExecutor();

  private ScheduledFuture<?> future;

  public static void main(String[] args) {
    final Random random = new Random();
    final SchedulerDemo schedulerDemo = new SchedulerDemo();

    new Thread(new Runnable() {
      @Override
      public void run() {
        schedulerDemo.testManualPeriodSchedule1(random);
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        schedulerDemo.testManualPeriodSchedule2(random);
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        schedulerDemo.testManualDelaySchedule(random);
      }
    }).start();
  }

  private void testManualPeriodSchedule2(final Random random) {
    final String taskName = "------------ Manual period scheduled task with fixed delay";

    manualSingleScheduler
      .scheduleWithFixedDelay(new ScheduledTask(taskName, random), 1, 1, TimeUnit.SECONDS);
  }

  private void testManualPeriodSchedule1(final Random random) {
    final String taskName = "++++++++++++ Manual period scheduled task at fix rate";

    this.future = manualSingleScheduler
      .scheduleAtFixedRate(new ScheduledTask(taskName, random), 1, 1, TimeUnit.SECONDS);
  }

  private void testManualDelaySchedule(final Random random) {
    final String taskName = "~~~~~~~~~~~~ Manual delay scheduled task";

    this.future = manualSingleScheduler.schedule(new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(taskName + " run at: " + new Date());
          if (future != null) {
            future.cancel(false);
          }

          int randomNum = random.nextInt(1010);
          TimeUnit.MILLISECONDS.sleep(randomNum);

          System.out.println(taskName + " schedule next task after sleep " + randomNum + " ms");
          testManualDelaySchedule(random);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, 1, TimeUnit.SECONDS);
  }

}
