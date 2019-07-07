package com.base.liam;

import java.util.Objects;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

public class IntersectString {

  public static void main(String[] args) {

    String s1 = "aabcc";
    String s2 = "dbbca";
    String s3 = "aadbbbaccc";
    String s4 = "aadbbcbcac";

    String sr1 = StringUtils.reverse(s1);
    String sr2 = StringUtils.reverse(s2);
    String sr3 = StringUtils.reverse(s3);
    String sr4 = StringUtils.reverse(s4);

    System.out.println("1. " + isIntersect2(s1, s2, s3));
    System.out.println("2. " + isIntersect2(s1, s2, s4));
    System.out.println("3. " + isIntersect2(sr1, sr2, sr3));
    System.out.println("4. " + isIntersect2(sr1, sr2, sr4));

    System.out.println(isIntersectBy(s1, s2, s3));
    System.out.println(isIntersectBy(s1, s2, s4));
    System.out.println(isIntersectBy(sr1, sr2, sr3));
    System.out.println(isIntersectBy(sr1, sr2, sr4));
  }

  public static boolean isIntersectBy(String s1, String s2, String s3) {
    Stack<Character> s = new Stack<>();
    for (Character c : s3.toCharArray()) {
      s.push(c);
    }
    for (int i = s1.length() - 1, j = s2.length() - 1; i >= 0 && j >= 0; ) {
      if (s.peek() == s1.charAt(i)) {
        s.pop();
        --i;
      } else if (s.peek() == s2.charAt(j)) {
        s.pop();
        --j;
      } else {
        return false;
      }
    }
    return s.isEmpty();
  }

  public static boolean isIntersect2(String s1, String s2, String s3) {
    char[] chars1 = s1.toCharArray();
    char[] chars2 = s2.toCharArray();
    char[] chars3 = s3.toCharArray();

    int len1 = chars1.length;
    int len2 = chars2.length;
    int len3 = chars3.length;

    if (len1 + len2 != len3) {
      return false;
    }

    int i = len1 - 1;
    int j = len2 - 1;
    int k = len3 - 1;
    while (k > 0 && i >= 0 && j >= 0) {
      if (chars3[k] == chars1[i]) {
        k--;
        i--;
        continue;
      }
      if (chars3[k] == chars2[j]) {
        k--;
        j--;
        continue;
      }
      return false;
    }
    return k == 0;
  }

  public static boolean isIntersect(String s1, String s2, String s3) {
    boolean r1 = false, r2 = false;

    if (Objects.equals(s1, "") && Objects.equals(s2, "") && Objects.equals(s3, "")) {
      return true;
    }
    if (!Objects.equals(s1, "") && !Objects.equals(s3, "") && s1.charAt(0) == s3.charAt(0)) {
      r1 = isIntersect(s1.substring(1), s2, s3.substring(1));
    }
    if (!Objects.equals(s2, "") && !Objects.equals(s3, "") && s2.charAt(0) == s3.charAt(0)) {
      r2 = isIntersect(s1, s2.substring(1), s3.substring(1));
    }

    return r1 || r2;
  }
}
