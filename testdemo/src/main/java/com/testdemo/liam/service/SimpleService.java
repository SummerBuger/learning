package com.testdemo.liam.service;

import com.google.common.collect.Lists;
import com.testdemo.liam.po.Param;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.list.UnmodifiableList;

import java.util.Collections;
import java.util.List;

/**
 * Created by liam on 2016/12/19.
 */
public class SimpleService {


  public static Long staticMethod() {
    return -1L;
  }

  public List<Integer> mockMethod() {
    System.out.println("get the empty list ");
    return Collections.emptyList();
  }

  public List<Integer> callMockMethod() {
    if (CollectionUtils.isEmpty(mockMethod())) {
      return Collections.emptyList();
    } else {
      return Lists.newArrayList(1);
    }
  }

  public static Long callStaticMethod(List<Param> params) {
    if (CollectionUtils.isEmpty(params)) {
      throw new IllegalArgumentException();
    }

    long count = 0;
    for (Param param : params) {
      count += param.getCount();
    }

    if (count == 0) {
      return staticMethod();
    }
    return count;
  }

  private int privateMethod() {
    return 0;
  }

  public int callPrivateMethod() {
    int i = privateMethod();
    if (i == 0) {
      return 0;
    }
    return i - 1;
  }

  UnmodifiableList getUnmodifiedList() {
    return null;
  }
}
