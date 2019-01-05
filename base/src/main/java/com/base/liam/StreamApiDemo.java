package com.base.liam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiDemo {

  public static void main(String[] args) {
    List<Integer> integerList = Arrays.asList(12, 32, 43, 42, 41, 53, 23, 556, 13, 18, 123);
    System.out.println(integerList.stream().count());
    boolean b = integerList.stream().allMatch(integer -> integer > 30);
    System.out.println("integer list all great than 30, " + b);
    System.out.println(integerList.stream().count());
    boolean b2 = integerList.stream().anyMatch(integer -> integer > 30);
    System.out.println("integer list any great than 30, " + b);
    System.out.println(integerList.stream().count());

    List<Integer> list = integerList.stream().sorted().collect(Collectors.toList());
    System.out.println(list);

    integerList.forEach(integer -> System.out.print(integer + ","));

  }

}
