package com.base.liam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaDemo {

  public static void main(String[] args) {

    List<Integer> integerList = Arrays.asList(12, 21, 33, 42, 54, 31);
    integerList.forEach(System.out::print);
    System.out.println("\n---------------------------------------------------");
    integerList.forEach(integer -> System.out.print(integer + ", "));
    System.out.println("\n---------------------------------------------------");

    Collections.sort(integerList, (Integer i1, Integer i2) -> {
      return Integer.compare(i1, i2);
    });
    integerList.forEach(integer -> System.out.print(integer + ", "));
    System.out.println("\n---------------------------------------------------");

    Collections.sort(integerList, (Integer i1, Integer i2) -> Integer.compare(i1, i2));
    integerList.forEach(integer -> System.out.print(integer + ", "));
    System.out.println("\n---------------------------------------------------");

    Collections.sort(integerList, Integer::compare);
    integerList.forEach(integer -> System.out.print(integer + ", "));
    System.out.println("\n---------------------------------------------------");

    Function<String, Integer> convertIntFunc = Integer::parseInt;
    Integer i1 = convertIntFunc.apply("101");
    System.out.println(i1);
    System.out.println("---------------------------------------------------");

    Supplier<List<String>> listOfString = ArrayList::new;
    List<String> res = listOfString.get();

    testRunnable();

  }

  private static void testRunnable() {
    Runnable r1 = () -> System.out.println("Hi, lambda runnable -- " + Thread.currentThread().getName());
    r1.run();
    new Thread(r1).start();
  }

}
