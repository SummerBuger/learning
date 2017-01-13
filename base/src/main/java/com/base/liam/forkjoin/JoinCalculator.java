package com.learn.java.forkjoin;


import java.util.concurrent.*;

/**
 * Created by chaochun.ccc on 2016/12/26.
 */
public class JoinCalculator extends RecursiveTask<Integer> {

  private static final Integer THREAD_HOLD = 100;

  private int start;
  private int end;

  public JoinCalculator(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  @Override
  protected Integer compute() {

    int sum = 0;
    boolean canCal = (end - start) <= THREAD_HOLD;
    if (canCal) {
      for (int i = start; i <= end; i++) {
        sum += i;
      }
//      System.out.println("calculate from " + start + " to " + end + " = " + sum);
    } else {
      int mid = (start + end) / 2;

      JoinCalculator leftCalculator = new JoinCalculator(start, mid);
      JoinCalculator rightCalculator = new JoinCalculator(mid + 1, end);
      leftCalculator.fork();
      rightCalculator.fork();
      Integer leftResult = leftCalculator.join();
      Integer rightResult = rightCalculator.join();
      sum = leftResult + rightResult;
    }

    return sum;
  }

  public static void main(String[] args) {
    int a = 1, b = 1000;

    long begin = System.currentTimeMillis();
    JoinCalculator calculator = new JoinCalculator(a, b);
    ForkJoinPool pool = new ForkJoinPool();
    Future<Integer> submit = pool.submit(calculator);
    try {
      Integer res = submit.get();
//      System.out.println(res);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println("============== " + (System.currentTimeMillis() - begin));
    begin = System.currentTimeMillis();
    int sum = 0;
    for (int i = a; i <= b; i++) {
      sum += i;
    }
    System.out.println("============== " + (System.currentTimeMillis() - begin));
  }
}
