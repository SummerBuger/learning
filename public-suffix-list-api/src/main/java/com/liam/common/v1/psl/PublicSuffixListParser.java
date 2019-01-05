package com.liam.common.v1.psl;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chaochun.ccc on 2017-06-01.
 */
public class PublicSuffixListParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(PublicSuffixListParser.class);


  /**
   * The rule line pattern.
   */
  private static final Pattern RULE_LINE = Pattern.compile("^(\\S+)");

  /**
   * The comment line pattern.
   */
  private static final Pattern COMMENT_LINE = Pattern.compile("^//.*$");

  /**
   * The white space line pattern.
   */
  private static final Pattern WHITE_SPACE_LINE = Pattern.compile("^\\s*$");

  /**
   * The line begin with !
   */
  private static final Pattern EXCLUDE = Pattern.compile("^\\!.*$");

  public static void main(String[] args) {
    boolean matches = EXCLUDE.matcher("test").matches();
    System.out.println(matches);
  }

  /**
   * The line begin with "*."
   */
  private static final Pattern UNDER = Pattern.compile("^(\\*\\.\\S+)");

  private static final ImmutableMap<PublicSuffixListType, Pattern> PSL_TYPE_PATTERN_MAP =
      ImmutableMap.<PublicSuffixListType, Pattern>builder()
          .put(PublicSuffixListType.EXACT, RULE_LINE).put(PublicSuffixListType.EXCLUDE, EXCLUDE)
          .put(PublicSuffixListType.UNDER, UNDER).build();

  public static Map<PublicSuffixListType, List<String>> parseToMap(InputStream stream) {
    Map<PublicSuffixListType, List<String>> map = Maps.newHashMap();
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

    String line;
    try {
      while ((line = reader.readLine()) != null) {
        Map.Entry<PublicSuffixListType, String> entry = parseLine(line);
        if (entry == null) {
          continue;
        }
        PublicSuffixListType key = entry.getKey();
        if (map.containsKey(key)) {
          map.get(key).add(entry.getValue());
        } else {
          map.put(key, Lists.newArrayList(entry.getValue()));
        }
      }
      return map;
    } catch (IOException e) {
      LOGGER.error("parse the public suffix list input stream get error", e);
      throw new IllegalStateException("Failed to parse public suffix list", e);
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }

  private static Map.Entry<PublicSuffixListType, String> parseLine(String line) {
    if (line == null) {
      return null;
    }
    String trimmedLine = line.trim();
    if (COMMENT_LINE.matcher(trimmedLine).matches()) {
      return null;
    }
    if (WHITE_SPACE_LINE.matcher(trimmedLine).matches()) {
      return null;
    }
    Matcher matcher = RULE_LINE.matcher(trimmedLine);
    if (!matcher.find()) {
      return null;
    }
    if (EXCLUDE.matcher(line).matches()) {
      line = line.substring(1, line.length());
      return new AbstractMap.SimpleEntry<>(PublicSuffixListType.EXCLUDE, line);
    } else if (UNDER.matcher(line).matches()) {
      line = line.substring(2, line.length());
      return new AbstractMap.SimpleEntry<>(PublicSuffixListType.UNDER, line);
    } else {
      return new AbstractMap.SimpleEntry<>(PublicSuffixListType.EXACT, line);
    }
  }

  public static List<String> parse(InputStream stream, PublicSuffixListType type) {

    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

    List<String> publicSuffixList = Lists.newArrayList();
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        String publicSuffix = parseLine(line, type);
        if (Strings.isNullOrEmpty(publicSuffix)) {
          continue;
        }
        publicSuffixList.add(publicSuffix);
      }
      return publicSuffixList;
    } catch (IOException e) {
      LOGGER.warn("parse the public suffix list input stream get error", e);
      return Collections.emptyList();
    }
  }

  private static String parseLine(String line, PublicSuffixListType type) {
    if (line == null) {
      return null;
    }
    String trimmedLine = line.trim();
    if (COMMENT_LINE.matcher(trimmedLine).matches()) {
      return null;
    }
    if (WHITE_SPACE_LINE.matcher(trimmedLine).matches()) {
      return null;
    }
    Matcher matcher = RULE_LINE.matcher(trimmedLine);
    if (!matcher.find()) {
      return null;
    }
    if (UNDER.matcher(line).matches() && PublicSuffixListType.UNDER.equals(type)) {
      line = line.substring(1, line.length());
      return line;
    } else if (EXCLUDE.matcher(line).matches() && PublicSuffixListType.EXCLUDE.equals(type)) {
      line = line.substring(2, line.length());
      return line;
    } else if (PublicSuffixListType.EXACT.equals(type)) {
      return line;
    }
    return null;
  }
}
