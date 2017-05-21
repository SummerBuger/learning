package com.liam.learn.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by chaochun.ccc on 2017-03-20.
 */
public class ThreadPoolDemo {

  private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolDemo.class);

  public static void main(String[] args) {
    testBatchRun();
  }

  public static void testBatchRun() {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
            new RejectedExecutionHandler() {
              @Override
              public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                LOGGER.warn("被 reject 了！");
                System.exit(-1);
              }
            });

    Semaphore semaphore = new Semaphore(3);
    LOGGER.info("开始批量运行线程");
    while (true) {
      executor.execute(new SimpleTask(semaphore));
    }
  }

  public static void testShutDown() {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    executor.execute(new Runnable() {
      @Override
      public void run() {
        while (true) {
          LOGGER.info("我是杀不死的小线程！");
          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    executor.shutdown();
    while (!executor.isTerminated());
    System.out.println("不死小线程，，呵呵，我等你死！");
  }

  private static class SimpleTask implements Runnable {

    private Semaphore semaphore;

    public SimpleTask(Semaphore semaphore) {
      this.semaphore = semaphore;
    }

    @Override
    public void run() {
      try {
        semaphore.acquire();
        LOGGER.info("子线程 {} 开始了！", Thread.currentThread().getName());
        int i = ThreadLocalRandom.current().nextInt(10);
        TimeUnit.SECONDS.sleep(i);
        LOGGER.info("子线程 {} 睡了 {}s ,结束了！",  Thread.currentThread().getName(), i);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        semaphore.release();
      }
    }
  }

}
