package com.liam.learn.javapdemo;

/**
 * Created by chaochun.ccc on 2017-03-29.
 */
public class JavaPDemo {

  public static void main(String[] args) {
    synchronized (JavaPDemo.class) {

    }
    m();
  }

  public static synchronized void m() {

  }

}
