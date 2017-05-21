package com.guavademo.liam;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * Created by liam on 2017-01-18.
 */
public class FilesDemo {

  public static void main(String[] args) {
    String[] arr = new String[] {
            "test1", "test2", "test3"
    };
    String path = "/Users/liam/mlearn/projs/learning/guavademo/target/classes/test/test.log";
    System.out.println(path);
    File file = new File(path);
    boolean hasParent = file.getParentFile().exists();
    System.out.println(hasParent);
    if (!hasParent) {
      file.getParentFile().mkdir();
    }

    System.out.println("absolute path: " + file.getAbsolutePath());
    System.out.println("path: " + file.getPath());
    try {
      System.out.println("canonical path: " + file.getCanonicalPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(file.exists());
    for (String s : arr) {
      try {
//        Files.write(s.getBytes(), file);
        Files.append(s + "\r\n", file, Charsets.UTF_8);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
