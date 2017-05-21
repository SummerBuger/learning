package com.base.liam;

/**
 * Created by chaochun.ccc on 2017-05-14.
 */
public class InnerClassDemo {

  {
    System.out.println("loading the outer class!");
  }

  static class StaticInnerClass {
    {
      System.out.println("loading the static inner class!");
    }
  }

  class SimpleInnerClass {
    {
      System.out.println("loading the not static inner class!");
    }
  }

  public static void main(String[] args) {
    InnerClassDemo innerClassDemo =  new InnerClassDemo();
    System.out.println("======= 分割线 =====");
    StaticInnerClass staticInnerClass = new StaticInnerClass();
    InnerClassDemo.SimpleInnerClass simpleInnerClass = innerClassDemo.new SimpleInnerClass();
    System.out.println(simpleInnerClass);
    System.out.println(staticInnerClass);
  }
}
