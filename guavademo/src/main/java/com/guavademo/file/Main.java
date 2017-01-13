package com.guavademo.file;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by chaochun.ccc on 2017/1/4.
 */
public class Main {

  public static void main(String[] args) {
    ArrayList<Projects> projectsList = Lists.newArrayList(Projects.values());
    List<JarBaseInfo> tranform = new JarInfoTransfor().tranform(projectsList);

    Collections.sort(tranform, new Comparator<JarBaseInfo>() {
      public int compare(JarBaseInfo o1, JarBaseInfo o2) {
        String s1 = o1.getGroup() + o1.getArtifactId();
        String s2 = o2.getGroup() + o2.getArtifactId();
        return s1.compareTo(s2);
      }
    });

    for (JarBaseInfo jarBaseInfo : tranform) {
      System.out.println(jarBaseInfo);
    }
  }

}
