package com.liam.learn.forkjoin;

import java.util.concurrent.*;

/**
 * Created by chaochun.ccc on 2017-03-17.
 */
public class ForkJoinDemo {

  public static void main(String[] args) {
    ForkJoinPool forkJoinPool = new ForkJoinPool(2);
    forkJoinPool.execute(new ForkAction("test1"));
    forkJoinPool.execute(new ForkAction("test222"));
    new ForkAction("test333").fork();


  }

  private static class ForkAction extends RecursiveAction {

    private String name;

    public ForkAction(String name) {
      this.name = name;
    }

    @Override
    protected void compute() {
      System.out.println("begin the first RecursiveAction: " + name);
      int sleep = ThreadLocalRandom.current().nextInt(15);
      try {
        TimeUnit.SECONDS.sleep(sleep);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(
          "finished the first RecursiveAction " + name + " after sleep " + sleep + " second");
    }
  }

}
