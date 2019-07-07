package com.springdemo.liam.vo;

import java.util.Map;

public class MapParam {

  private String name;

  private int age;

  private Map<String, Object> extendInfo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Map<String, Object> getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(Map<String, Object> extendInfo) {
    this.extendInfo = extendInfo;
  }
}
