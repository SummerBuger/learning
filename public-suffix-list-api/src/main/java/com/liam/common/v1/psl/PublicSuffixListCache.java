package com.liam.common.v1.psl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-06-01.
 */
public class PublicSuffixListCache {
  private static final Logger LOGGER = LoggerFactory.getLogger(PublicSuffixListCache.class);

  private static final String PUBLIC_SUFFIX_LIST_DAT_URL =
      "https://publicsuffix.org/list/effective_tld_names.dat";

  private PublicSuffixListCache() {
    URL url;
    try {
      url = new URL(PUBLIC_SUFFIX_LIST_DAT_URL);
    } catch (MalformedURLException e) {
      throw new IllegalStateException(e);
    }
    InputStream listStream = null;
    try {
      listStream = url.openStream();
      Map<PublicSuffixListType, List<String>> publicSuffixListTypeListMap =
          PublicSuffixListParser.parseToMap(listStream);
      Map<PublicSuffixListType, ImmutableList<String>> immutableMap = Maps.newHashMap();
      for (Map.Entry<PublicSuffixListType, List<String>> entry : publicSuffixListTypeListMap
          .entrySet()) {
        immutableMap.put(entry.getKey(), ImmutableList.copyOf(entry.getValue()));
      }
      pslCache.putAll(immutableMap);
      System.out.println(this.getExcludePublicSuffixList().equals(PublicSuffixPatterns.EXCLUDED.keySet()));
      System.out.println(this.getExtraPublicSuffixList().equals(PublicSuffixPatterns.EXACT.keySet()));
      System.out.println(this.getUnderPublicSuffixList().equals(PublicSuffixPatterns.UNDER.keySet()));
    } catch (IOException e) {
      LOGGER.error("parse the public suffix list input stream get error", e);
      throw new IllegalStateException("Failed to loading the public suffix list", e);
    }
  }

  private LoadingCache<PublicSuffixListType, ImmutableList<String>> pslCache =
      CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS)
          .build(new CacheLoader<PublicSuffixListType, ImmutableList<String>>() {
            @Override
            public ImmutableList<String> load(PublicSuffixListType key) throws Exception {
              URL url;
              try {
                url = new URL(PUBLIC_SUFFIX_LIST_DAT_URL);
              } catch (MalformedURLException e) {
                throw new IllegalStateException(e);
              }
              InputStream listStream = url.openStream();
              List<String> parsePublicSuffix = PublicSuffixListParser.parse(listStream, key);
              return ImmutableList.copyOf(parsePublicSuffix);
            }
          });

  public ImmutableList<String> getExtraPublicSuffixList() {
    try {
      return pslCache.get(PublicSuffixListType.EXACT);
    } catch (ExecutionException e) {
      LOGGER.error("the error occurred while get the extra public suffix list", e);
      return ImmutableList.copyOf(Collections.<String>emptyList());
    }

  }

  public ImmutableList<String> getExcludePublicSuffixList() {
    try {
      return pslCache.get(PublicSuffixListType.EXCLUDE);
    } catch (ExecutionException e) {
      LOGGER.error("the error occurred while get the include public suffix list", e);
      return ImmutableList.copyOf(Collections.<String>emptyList());
    }
  }

  public ImmutableList<String> getUnderPublicSuffixList() {
    try {
      return pslCache.get(PublicSuffixListType.UNDER);
    } catch (ExecutionException e) {
      LOGGER.error("the error occurred while get the under public suffix list", e);
      return ImmutableList.copyOf(Collections.<String>emptyList());
    }
  }

  public static PublicSuffixListCache newInstance() {
    System.out.println("call the new newInstance");
    return INSTANCE;
  }

  private static final PublicSuffixListCache INSTANCE = new PublicSuffixListCache();
}
