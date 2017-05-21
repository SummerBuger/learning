package com.base.liam;

/**
 * Created by chaochun.ccc on 2017-04-01.
 */
public class BitOperateDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 1000; i++) {
      int a = 0x1 & (i >> 3);
      if (a == 0) {
        System.out.println(i + "， " + Integer.toBinaryString(i));
      }
    }
  }
}
