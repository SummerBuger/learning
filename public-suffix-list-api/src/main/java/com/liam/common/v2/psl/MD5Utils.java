package com.liam.common.v2.psl;

import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chaochun.ccc on 2017-06-11.
 */
public class MD5Utils {

  public MD5Utils() {}

  public static String fastMD5(byte[] bytes) throws IOException {
    if (bytes == null) {
      return "";
    } else {
      MessageDigest digest = null;

      try {
        digest = MessageDigest.getInstance("MD5");
        digest.update(bytes);
      } catch (NoSuchAlgorithmException var3) {
        ;
      }
      return digest == null ? "" : Hex.encodeHexString(digest.digest());
    }
  }
}
