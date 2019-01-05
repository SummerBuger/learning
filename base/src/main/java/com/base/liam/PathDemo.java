package com.base.liam;

/**
 * Created by chaochun.ccc on 2017-06-02.
 */
public class PathDemo {

  public static void main(String[] args) {
    System.out.println(PathDemo.class.getResource("/").getPath());
    System.out.println(PathDemo.class.getResource("").getPath());
    System.out.println(PathDemo.class.getResource("test.txt"));
    System.out.println(PathDemo.class.getResource("/test.txt"));
    System.out.println(PathDemo.class.getClassLoader().getResource(""));
    System.out.println(PathDemo.class.getClassLoader().getResource("/"));

  }
}
