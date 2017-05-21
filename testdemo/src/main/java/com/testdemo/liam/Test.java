package com.testdemo.liam;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by chaochun.ccc on 2017-04-10.
 */
public class Test {

  public static final Splitter COMMA_SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

  private final static byte[] BOM_HEADER = new byte[]{-17, -69, -65};

  public static void main(String[] args) {
    String filePath = "/Users/liam/mlearn/projs/learning/testdemo/src/main/resources/white-list1491451413718.txt";
    File file = new File(filePath);
    List<String> strings = null;
    try {
      strings = Files.readLines(file, Charsets.UTF_8);
    } catch (IOException e) {

    }
    for (String string : strings) {
      List<String> list = COMMA_SPLITTER.splitToList(string);

      String bom = new String(BOM_HEADER);

      System.out.println(list.get(0).startsWith(bom));
      System.out.println(NumberUtils.isNumber(list.get(0)));
      System.out.println(string);
    }
  }
}
