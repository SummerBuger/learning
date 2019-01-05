package com.liam.common.v2.psl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chaochun.ccc on 2017-06-01.
 */
public class PublicSuffixSetCache {
  private static final Logger LOGGER = LoggerFactory.getLogger(PublicSuffixSetCache.class);

  private static final String PUBLIC_SUFFIX_LIST_DAT_URL =
      "https://publicsuffix.org/list/effective_tld_names.dat";

  private static final String PUBLIC_SUFFIX_LIST_LOCAL_PATH = "/public_suffix_list.dat";

  private ScheduledExecutorService fetchPslExecutor = Executors.newSingleThreadScheduledExecutor();

  private Lock fileWriteLock = new ReentrantLock();

  private PublicSuffixSetCache() {
    // 启动一个守护进程，定时拉取远程的文件
    fetchPublicSuffixList();
    // 启动时从本地文件获取数据
    fillCache();
  }

  private void fetchPublicSuffixList() {
    fetchPslExecutor.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        URL url;
        try {
          url = new URL(PUBLIC_SUFFIX_LIST_DAT_URL);
        } catch (MalformedURLException e) {
          throw new IllegalStateException(e);
        }
        FileOutputStream tmpFileOutputStream = null;
        ReadableByteChannel rbc = null;
        try {
          String path = getClass().getResource("/").getPath();
          File localFile = new File(path + PUBLIC_SUFFIX_LIST_LOCAL_PATH);
          if (md5Check(url, localFile)) {
            return;
          }
          LOGGER.debug("path getPath:{}", path);
          File tmpFile = new File(path + "readme.txt");
          rbc = Channels.newChannel(url.openStream());
          tmpFileOutputStream = new FileOutputStream(tmpFile);
          tmpFileOutputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
          boolean lock = fileWriteLock.tryLock(10, TimeUnit.SECONDS);
          if (lock) {
            boolean renameTo = tmpFile.renameTo(localFile);
            if (renameTo) {
              throw new IllegalStateException("重命名失败！");
            }
            LOGGER.debug("rename to local file {} result: {}", localFile.getPath(), renameTo);
          }
        } catch (InterruptedException e) {
          LOGGER.warn("fetchPublicSuffixList can not get file lock:{}", e);
        } catch (Exception e) {
          LOGGER.error("fetchPublicSuffixList get error:{}", e);
          throw new IllegalStateException(e);
        } finally {
          LOGGER.debug("finish fetch the remote public suffix list");
          IOUtils.closeQuietly(tmpFileOutputStream);
          IOUtils.closeQuietly(rbc);
        }
      }
    }, 0, 5, TimeUnit.SECONDS);
    pslCache.cleanUp();
  }

  private boolean md5Check(URL url, File localFile) throws IOException {
    InputStream isForMd5 = url.openStream();
    byte[] remoteBytes = IOUtils.toByteArray(isForMd5);
    String remoteMd5 = MD5Utils.fastMD5(remoteBytes);
    FileInputStream localStream = new FileInputStream(localFile);
    byte[] localBytes = IOUtils.toByteArray(localStream);
    String localMd5 = MD5Utils.fastMD5(localBytes);
    isForMd5.close();
    localStream.close();
    if (remoteMd5.equals(localMd5)) {
      LOGGER.debug("md5 值一致，不需要更新本地文件");
      return true;
    }
    return false;
  }

  private void fillCache() {
    long begin = System.currentTimeMillis();
    InputStream listStream = null;
    listStream = getClass().getResourceAsStream(PUBLIC_SUFFIX_LIST_LOCAL_PATH);
    LOGGER.debug("open stream used time:{}", System.currentTimeMillis() - begin);
    begin = System.currentTimeMillis();
    Map<PublicSuffixListType, List<String>> publicSuffixListTypeListMap =
        PublicSuffixListParser.parseToMap(listStream);
    LOGGER.debug("parse to map used time:{}", System.currentTimeMillis() - begin);
    begin = System.currentTimeMillis();
    Map<PublicSuffixListType, ImmutableSet<String>> immutableMap = Maps.newHashMap();
    for (Map.Entry<PublicSuffixListType, List<String>> entry : publicSuffixListTypeListMap
        .entrySet()) {
      immutableMap.put(entry.getKey(), ImmutableSet.copyOf(entry.getValue()));
    }
    LOGGER.debug("convert to immutable map used time:{}", System.currentTimeMillis() - begin);
    begin = System.currentTimeMillis();
    pslCache.putAll(immutableMap);
    LOGGER.debug("put all to cache used time:{}", System.currentTimeMillis() - begin);
    System.out.println("the construct");
  }

  private LoadingCache<PublicSuffixListType, ImmutableSet<String>> pslCache =
      CacheBuilder.newBuilder().expireAfterWrite(4, TimeUnit.SECONDS)
          .build(new CacheLoader<PublicSuffixListType, ImmutableSet<String>>() {
            @Override
            public ImmutableSet<String> load(PublicSuffixListType key) throws Exception {
              System.out.println("有没有发生loading 呢");
              InputStream listStream =
                  getClass().getResourceAsStream(PUBLIC_SUFFIX_LIST_LOCAL_PATH);
              List<String> parsePublicSuffix = PublicSuffixListParser.parse(listStream, key);
              return ImmutableSet.copyOf(parsePublicSuffix);
            }
          });

  public ImmutableSet<String> getExactPublicSuffixList() {
    long begin = System.currentTimeMillis();
    try {
      return pslCache.get(PublicSuffixListType.EXACT);
    } catch (ExecutionException e) {
      LOGGER.error("the error occurred while get the extra public suffix list", e);
      return ImmutableSet.copyOf(Collections.<String>emptyList());
    } finally {
      LOGGER.info("getExactPublicSuffixList used time: {}", System.currentTimeMillis() - begin);
    }

  }

  public ImmutableSet<String> getExcludePublicSuffixList() {
    long begin = System.currentTimeMillis();
    try {
      return pslCache.get(PublicSuffixListType.EXCLUDE);
    } catch (ExecutionException e) {
      LOGGER.error("the error occurred while get the include public suffix list", e);
      return ImmutableSet.copyOf(Collections.<String>emptyList());
    } finally {
      LOGGER.info("getExcludePublicSuffixList used time: {}", System.currentTimeMillis() - begin);
    }
  }

  public ImmutableSet<String> getUnderPublicSuffixList() {
    long begin = System.currentTimeMillis();
    try {
      return pslCache.get(PublicSuffixListType.UNDER);
    } catch (ExecutionException e) {
      LOGGER.error("the error occurred while get the under public suffix list", e);
      return ImmutableSet.copyOf(Collections.<String>emptyList());
    } finally {
      LOGGER.info("getUnderPublicSuffixList used time: {}", System.currentTimeMillis() - begin);
    }
  }

  public static PublicSuffixSetCache newInstance() {
    return SingleHolder.SINGLETON;
  }

  private static class SingleHolder {
    private static final PublicSuffixSetCache SINGLETON = new PublicSuffixSetCache();
  }

}
