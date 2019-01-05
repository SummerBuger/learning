package com.base.liam;

import com.google.common.base.Strings;

/**
 * Created by chaochun.ccc on 2017-03-08.
 */
public class Test {

  public static void main(String[] args) {
    int dbIdx = 0;

    while (dbIdx <= 7) {
      System.out.println(
          "#==============================================================================");


      String sql2 = "\n" + "ALTER TABLE wolong_000" + dbIdx
          + ".cpc_account_%s ADD `target_user_tracking` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否开启目标用户追投 1: 开启 0: 关闭';\n"
          + "ALTER TABLE wolong_000" + dbIdx
          + ".tb_plan_%s ADD `target_user_tracking` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否开启目标用户追投 1: 开启 0: 关闭';\n";
      for (int i = dbIdx; i < 8; i += 8) {
        String tbIdx = i + "";
        tbIdx = Strings.padStart(tbIdx, 4, '0');
        String format = String.format(sql2, tbIdx, tbIdx, tbIdx, tbIdx, tbIdx, tbIdx);
        System.out.println(format);
      }
      System.out.println(
          "#==============================================================================");
      dbIdx++;
    }
  }


  public static void main2() {

    int dbIdx = 3;

    while (dbIdx < 7) {

      String sql = "select city_hash from  wolong_000" + dbIdx
          + ".tb_idea_%s ADD `content` text NOT NULL DEFAULT '' ;\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_idea_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';";
      for (int i = dbIdx; i < 1024; i += 8) {
        String tbIdx = i + "";
        tbIdx = Strings.padStart(tbIdx, 4, '0');
        String format = String.format(sql, tbIdx, tbIdx);

        System.out.println(format);
      }

      System.out.println(
          "#==============================================================================");

      String sql2 = "\n" + "ALTER TABLE wolong_000" + dbIdx
          + ".tb_app_%s ADD `content` text NOT NULL DEFAULT '';\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_app_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';\n"
          + "ALTER TABLE wolong_000" + dbIdx
          + ".tb_phone_%s ADD `content` text NOT NULL DEFAULT '';\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_phone_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';\n"
          + "ALTER TABLE wolong_000" + dbIdx
          + ".tb_xijing_%s ADD `content` text NOT NULL DEFAULT '';\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_xijing_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';";
      for (int i = dbIdx; i < 8; i += 8) {
        String tbIdx = i + "";
        tbIdx = Strings.padStart(tbIdx, 4, '0');
        String format = String.format(sql2, tbIdx, tbIdx, tbIdx, tbIdx, tbIdx, tbIdx);
        System.out.println(format);
      }
      System.out.println(
          "#==============================================================================");
      dbIdx++;
    }

  }

  public static void test() {

    int dbIdx = 3;

    while (dbIdx < 7) {

      String sql = "ALTER TABLE wolong_000" + dbIdx
          + ".tb_idea_%s ADD `content` text NOT NULL DEFAULT '' ;\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_idea_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';";
      for (int i = dbIdx; i < 1024; i += 8) {
        String tbIdx = i + "";
        tbIdx = Strings.padStart(tbIdx, 4, '0');
        String format = String.format(sql, tbIdx, tbIdx);

        System.out.println(format);
      }

      System.out.println(
          "#==============================================================================");

      String sql2 = "\n" + "ALTER TABLE wolong_000" + dbIdx
          + ".tb_app_%s ADD `content` text NOT NULL DEFAULT '';\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_app_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';\n"
          + "ALTER TABLE wolong_000" + dbIdx
          + ".tb_phone_%s ADD `content` text NOT NULL DEFAULT '';\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_phone_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';\n"
          + "ALTER TABLE wolong_000" + dbIdx
          + ".tb_xijing_%s ADD `content` text NOT NULL DEFAULT '';\n" + "ALTER TABLE wolong_000"
          + dbIdx + ".tb_xijing_%s ADD `styletype` int(11) NOT NULL DEFAULT '0';";
      for (int i = dbIdx; i < 8; i += 8) {
        String tbIdx = i + "";
        tbIdx = Strings.padStart(tbIdx, 4, '0');
        String format = String.format(sql2, tbIdx, tbIdx, tbIdx, tbIdx, tbIdx, tbIdx);
        System.out.println(format);
      }
      System.out.println(
          "#==============================================================================");
      dbIdx++;
    }
  }



}
