package com.base.liam.enumtest;

import java.util.List;

/**
 * Created by chaochun.ccc on 2017-03-23.
 */
public class Test {

  public static void main(String[] args) {
    List<Integer> test1 = Demo.Test.test();
    List<Integer> test2 = Demo.Test.test();
    System.out.println(test1 == test2);
  }

}
