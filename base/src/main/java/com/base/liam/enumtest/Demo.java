package com.base.liam.enumtest;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by chaochun.ccc on 2017-03-23.
 */
public enum Demo {

  Test() {
    private final List<Integer> list = Lists.newArrayList(1,2,3);

    public List<Integer> test() {
      System.out.println("又来一次");
      return list;
    }
  };

  Demo() {
  }

  public abstract List<Integer> test();
}
