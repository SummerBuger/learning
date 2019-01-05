package com.base.liam;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chaochun.ccc on 2017-06-11.
 */
public class ReadWriteDemo {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReadWriteDemo.class);

  private static final String PUBLIC_SUFFIX_LIST_DAT_URL =
      "https://publicsuffix.org/list/effective_tld_names.dat";

  private static final String PUBLIC_SUFFIX_LIST_LOCAL_PATH = "/public_suffix_list.dat";

  private static ScheduledExecutorService readExecutor =
      Executors.newSingleThreadScheduledExecutor();
  private static ScheduledExecutorService writeExecutor =
      Executors.newSingleThreadScheduledExecutor();

  private static int latestCount = 11986;

  public static void main(String[] args) {

  }

  public static void testUrl() {

  }


  public static void writeSchedule() {
    writeExecutor.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        URL url;
        try {
          url = new URL(PUBLIC_SUFFIX_LIST_DAT_URL);
        } catch (MalformedURLException e) {
          LOGGER.error("create new url failed", e);
          throw new IllegalStateException(e);
        }
        FileChannel tmpFileChannel = null;
        ReadableByteChannel readableByteChannel = null;
        FileChannel localFileChannel = null;
        try {
          String path = getClass().getResource("/").getPath();
          File tmpFile = new File(path + "readme.txt");
          readableByteChannel = Channels.newChannel(url.openStream());
          tmpFileChannel = new FileOutputStream(tmpFile).getChannel();
          tmpFileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
          tmpFileChannel.close();
          tmpFileChannel = new FileInputStream(tmpFile).getChannel();

          File localFile = new File(path + PUBLIC_SUFFIX_LIST_LOCAL_PATH);
          localFileChannel = new RandomAccessFile(localFile, "rw").getChannel();
          FileLock fileLock = localFileChannel.tryLock();
          if (fileLock != null) {
            long i = localFileChannel.transferFrom(tmpFileChannel, 0, Long.MAX_VALUE);
            LOGGER.debug("transfer bytes:{} from tmp file ", i);
          }
        } catch (Exception e) {
          LOGGER.error("fetchPublicSuffixList get error:{}", e);
          throw new IllegalStateException(e);
        } finally {
          LOGGER.debug("finish fetch the remote public suffix list");
          IOUtils.closeQuietly(tmpFileChannel);
          IOUtils.closeQuietly(readableByteChannel);
          IOUtils.closeQuietly(localFileChannel);
        }
      }
    }, 0, 5, TimeUnit.SECONDS);
  }

  public static void readSchedule() {
    readExecutor.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        try {
          URL resource = ReadWriteDemo.class.getResource(PUBLIC_SUFFIX_LIST_LOCAL_PATH);
          String path = resource.getPath();
          List<String> data = Files.readLines(new File(path), Charsets.UTF_8);
          int size = data.size();
          if (size != latestCount) {
            LOGGER.info("数量不一致了！！！ size: {} , latestCount: {}", size, latestCount);
            System.out.println("数量不一致了！！！ size: " + size + ", latestCount: " + latestCount);
          } else {
            LOGGER.info("====================== size: {}" , size);
          }
          latestCount = size;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }, 4, 5, TimeUnit.SECONDS);
  }

}
