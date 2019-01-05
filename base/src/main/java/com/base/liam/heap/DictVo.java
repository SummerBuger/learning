package com.base.liam.heap;

public class DictVo {

  private Long id;

  private String key;

  private String name;

  private Long parentId;

  private LevelEnum levelEnum;

  private Integer index;

  public DictVo() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public LevelEnum getLevelEnum() {
    return this.levelEnum;
  }

  public void setLevelEnum(LevelEnum levelEnum) {
    this.levelEnum = levelEnum;
  }

  public Integer getIndex() {
    return this.index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void accept(DictVo o) {
  }

  public Object clone() {
    try {
      return super.clone();
    } catch (Exception var2) {
      throw new RuntimeException("clone not supported", var2);
    }
  }
}

