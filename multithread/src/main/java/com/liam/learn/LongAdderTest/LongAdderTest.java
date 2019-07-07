package com.liam.learn.LongAdderTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    testAtomicLong();
  }

  private static void testAtomicLong()
    throws InterruptedException, ExecutionException {
    AtomicLong atomicLong = new AtomicLong();
    System.out.println("begin with AtomicLong: " + atomicLong.longValue());

    Executor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
      Runtime.getRuntime().availableProcessors() + 1, 2, TimeUnit.SECONDS,
      new ArrayBlockingQueue<>(100));

    CompletionService<Long> completionService = new ExecutorCompletionService<>(
      executor);

    for (int i = 0; i < 100; i++) {
      completionService.submit(new TestAtomicLong("Thread-For-AtomicLong-" + i, atomicLong));
    }

    for (int i = 0; i < 100; i++) {
      Future<Long> future = completionService.take();
      Long tmpLongAdder = future.get();
//      System.out.println("future " + future + " get " + tmpLongAdder);
    }
    System.out.println("Final result: " + atomicLong.longValue());
  }

  private static void testLongAdder()
    throws InterruptedException, ExecutionException {
    LongAdder longAdder = new LongAdder();
    System.out.println("begin with LongAdder: " + longAdder.longValue());

    Executor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
      Runtime.getRuntime().availableProcessors() + 1, 2, TimeUnit.SECONDS,
      new ArrayBlockingQueue<>(100));

    CompletionService<Long> completionService = new ExecutorCompletionService<>(
      executor);

    for (int i = 0; i < 100; i++) {
      completionService.submit(new TestLongAdderTask("Thread-For-LongAdder-" + i, longAdder));
    }

    for (int i = 0; i < 100; i++) {
      Future<Long> future = completionService.take();
      Long tmpLongAdder = future.get();
      System.out.println("future " + future + " get " + tmpLongAdder);
    }
    System.out.println("Final result: " + longAdder.longValue());
  }

  private static class TestLongAdderTask implements Callable<Long> {

    private String name;

    private LongAdder longAdder;

    public TestLongAdderTask(String name, LongAdder longAdder) {
      this.name = name;
      this.longAdder = longAdder;
    }

    @Override
    public Long call() throws Exception {
      long to = 0;
      try {
        TimeUnit.SECONDS.sleep(1);
        int randomInt = ThreadLocalRandom.current().nextInt(10);
        longAdder.add(randomInt);
        to = longAdder.longValue();
        System.out
          .println(this.name + " add " + randomInt + " to LongAdder get " + to);
      } finally {
        return to;
      }
    }
  }

  private static class TestAtomicLong implements Callable<Long> {

    private String name;

    private AtomicLong atomicLong;

    public TestAtomicLong(String name, AtomicLong atomicLong) {
      this.name = name;
      this.atomicLong = atomicLong;
    }

    @Override
    public Long call() throws Exception {
      long to = 0;
      try {
        TimeUnit.SECONDS.sleep(1);
        int randomInt = ThreadLocalRandom.current().nextInt(10);
        to = atomicLong.addAndGet(randomInt);
        System.out
          .println(this.name + " add " + randomInt + " to LongAdder get " + to);
      } finally {
        return to;
      }
    }
  }

}
