package com.base.liam.inherit;

public class Main {

  public static void main(String[] args) {
    Test test = new Test(new SimpleService());
    System.out.println(test);
    test.invoke();
  }

}
