package com.liam.learn.atomicdemo;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by chaochun.ccc on 2017-05-14.
 */
public class AtomicIntArrDemo {

  public static void main(String[] args) {
    int[] arr = new int[] {1,2,3};
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
    int i = atomicIntegerArray.addAndGet(0, 3);
    System.out.println(i);
    System.out.println(arr[0]);
    System.out.println(atomicIntegerArray.get(0));
  }

}
