package com.liam.common.v1.psl;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.net.InternetDomainName;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by chaochun.ccc on 2017-06-02.
 */
public class V1Demo {

  public static void main(String[] args) throws InterruptedException, IOException {
    RandomAccessFile file = new RandomAccessFile("/Users/liam/test/psl/public_suffix_list.dat", "r");
    FileChannel channel = file.getChannel();
    System.out.println(channel.size());
    ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
    int size = channel.read(buffer);
    System.out.println(size);
    byte[] bytes = buffer.array();
    ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
    List<String> lines = IOUtils.readLines(stream, Charsets.UTF_8);
    System.out.println(lines.size());

    File file2 = new File("/Users/liam/test/psl/public_suffix_list.2.dat");
    for (String line : lines) {
      Files.append(line, file2, Charsets.UTF_8);
    }

  }

  public static void test() {
    long begin = System.currentTimeMillis();
    AdvancedInternetDomainName advancedInternetDomainName =
        AdvancedInternetDomainName.from("a.com.io");
    System.out.println("from() used:" + (System.currentTimeMillis() - begin));
    begin = System.currentTimeMillis();
    AdvancedInternetDomainName publicSuffix = advancedInternetDomainName.publicSuffix();
    System.out.println("publicSuffix() used:" + (System.currentTimeMillis() - begin));
    System.out.println("publicSuffix result: " + publicSuffix);
  }


  public static void testGuava() {

    InternetDomainName publicSuffix = InternetDomainName.from("bad.bad").publicSuffix();
    System.out.println(publicSuffix);
  }
}
