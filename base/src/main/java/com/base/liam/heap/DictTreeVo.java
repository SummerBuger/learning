package com.base.liam.heap;

import java.util.List;

public class DictTreeVo extends DictVo {

  private List<DictTreeVo> children;

  public DictTreeVo() {
  }

  public List<DictTreeVo> getChildren() {
    return this.children;
  }

  public void setChildren(List<DictTreeVo> children) {
    this.children = children;
  }
}
