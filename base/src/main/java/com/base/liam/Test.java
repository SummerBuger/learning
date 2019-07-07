package com.base.liam;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by chaochun.ccc on 2017-03-08.
 */
public class Test {

  public static void main(String[] args) {
    String s = "cba";
    String t = "abcd";
    String res = new Test().customSortString(s, t);
    System.out.println(res);
  }

  public static void main0(String[] args) {
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

  public String customSortString(String S, String T) {
    if (T == null || T.length() <= 0) {
      return T;
    }

    char[] sortedArr = S.toCharArray();
    final List<Character> sortedList = toList(sortedArr);

    for (Character c : sortedList) {
      System.out.print(c + " ,");
    }

    char[] source = T.toCharArray();
    Character[] boxedSource = toBoxedList(source);

    Arrays.sort(boxedSource, new Comparator<Character>() {
      @Override
      public int compare(Character o1, Character o2) {

        int idx1 = sortedList.indexOf(o1);
        int idx2 = sortedList.indexOf(o2);

        System.out
          .println("compare o1: " + o1 + ", and o2: " + o2 + ", idx1: " + idx1 + ", idx2: " + idx2);

        if (idx1 >= 0 || idx2 >= 0) {
          return idx1 - idx2;
        }

        return o1.compareTo(o2);
      }
    });

    System.out.println("\n===============================");
    for (Character c : boxedSource) {
      System.out.print(c + " ,");
    }
    System.out.println("\n===============================");

    StringBuilder sbr = new StringBuilder();
    for (Character c : boxedSource) {
      sbr.append(c);
    }
    Maps.newHashMap().entrySet().iterator().next().getValue();
    return sbr.toString();
  }

  private Character[] toBoxedList(char[] charArr) {
    Character[] result = new Character[charArr.length];
    for (int i = 0; i < charArr.length; i++) {
      result[i] = charArr[i];
    }

    return result;
  }

  private List<Character> toList(char[] charArr) {
    List<Character> list = new ArrayList<>();

    for (char c : charArr) {
      list.add(c);
    }
    return list;
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
