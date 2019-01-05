package com.base.liam.repeatedReadStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RepeatedReadInputStream {

  public static void main(String[] args) throws IOException {
    File file = new File("/Users/liam/mlearn/projs/learning/base/src/main/resources/test.txt");
    BufferedReader reader = new BufferedReader(new FileReader(file));
    if (reader.markSupported()) {
      System.out.println("============= supported mark");
    } else {
      System.out.println("============= do not supported mark");
    }

    System.out.println(file.length());
    reader.mark((int)file.length() + 1);

    String line;
    while ((line = reader.readLine()) != null) {
      System.out.println(line.length() + " : " + line);
    }

    reader.reset();
    System.out.println("==========================================");

    while ((line = reader.readLine()) != null) {
      System.out.println(line.length() + " : " + line);
    }

  }

}
