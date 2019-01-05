package com.guavademo.liam.eventloopdemo;

import java.util.Date;

/**
 * Created by chaochun.ccc on 2017-05-21.
 */
public class SimpleEvent {

  private String name;
  private String content;
  private Date createTime;

  public String getName() {
    return name;
  }

  public SimpleEvent setName(String name) {
    this.name = name;
    return this;
  }

  public String getContent() {
    return content;
  }

  public SimpleEvent setContent(String content) {
    this.content = content;
    return this;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public SimpleEvent setCreateTime(Date createTime) {
    this.createTime = createTime;
    return this;
  }

  @Override
  public String toString() {
    return "SimpleEvent{" +
            "name='" + name + '\'' +
            ", content='" + content + '\'' +
            ", createTime=" + createTime +
            '}';
  }
}
