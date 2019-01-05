package com.base.liam.niodemo.selector;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Created by chaochun.ccc on 2017-07-16.
 */
public class SelectorDemo {

  public static void main(String[] args) throws IOException {
    try {
      Selector selector = Selector.open();
      RandomAccessFile randomAccessFile =  new RandomAccessFile("/test.txt", "rw");
      FileChannel channel = randomAccessFile.getChannel();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
