package com.testdemo.liam.po;

/**
 * Created by chaochun.ccc on 2017-03-15.
 */
public class Param {

  private String name;

  private Long count;

  public String getName() {
    return name;
  }

  public Param setName(String name) {
    this.name = name;
    return this;
  }

  public Long getCount() {
    return count;
  }

  public Param setCount(Long count) {
    this.count = count;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Param param = (Param) o;

    if (name != null ? !name.equals(param.name) : param.name != null) return false;
    return count != null ? count.equals(param.count) : param.count == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (count != null ? count.hashCode() : 0);
    return result;
  }
}
