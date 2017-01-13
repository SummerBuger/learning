package com.guavademo.file;

/**
 * Created by chaochun.ccc on 2017/1/4.
 */
public enum Projects {

  WOLONG_PLATFORM(1, "/Users/chaochun.ccc/alibaba/biz-learn/wolong-platform.denp.source"),
  CPC(2, "/Users/chaochun.ccc/alibaba/biz-learn/website.denp.source"),
  AD_BIZ(3, "/Users/chaochun.ccc/alibaba/biz-learn/ad-biz.denp.source"),
  API(4, "/Users/chaochun.ccc/alibaba/biz-learn/api.denp.source"),
  REPORT(5, "/Users/chaochun.ccc/alibaba/biz-learn/report-lib.source");

  private final  int order;
  private final  String fileName;

  public int getOrder() {
    return order;
  }

  public String getFileName() {
    return fileName;
  }

  Projects(int order, String fileName) {
    this.order = order;
    this.fileName = fileName;
  }
}
