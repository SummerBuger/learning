package com.guavademo.file;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by chaochun.ccc on 2017/1/4.
 */
public class FileReader {

  public static final Splitter MAOHAO_SPLITTER = Splitter.on(":").trimResults().omitEmptyStrings();

  public static final Joiner ENTER_JOINER = Joiner.on(" \\\\ ").skipNulls();

  public Map<Projects, Map<String, String>> readLines(List<Projects> projectss) {
    Map<Projects, Map<String,String>> result = Maps.newHashMap();
    for (Projects projects : projectss) {
      result.put(projects, readLines(projects.getFileName()));
    }
    return result;
  }

  private Map<String, String> readLines(String fileName) {
    URL prePath = this.getClass().getClassLoader().getResource("");
    String path =  prePath + fileName;
    System.out.println(path);

    File file = new File(fileName);
    try {
      Map<String, String> jarBaseInfo = Files.readLines(file, Charsets.UTF_8, new JarInfoLineProcessor());
      return jarBaseInfo;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Maps.newHashMap();
  }
}

class JarInfoLineProcessor implements LineProcessor<Map<String, String>> {

  private Map<String, String> jarInfos = Maps.newHashMap();

  public boolean processLine(String s) throws IOException {
    List<String> list = FileReader.MAOHAO_SPLITTER.splitToList(s);
    String uniqKey = list.get(0) + ":" + list.get(1);
    String ver = list.get(2);

    if (jarInfos.containsKey(uniqKey)) {
      ver = jarInfos.get(uniqKey) + ";" + ver;
    }
    jarInfos.put(uniqKey, ver);
    return true;
  }

  public Map<String, String> getResult() {
    return jarInfos;
  }
}