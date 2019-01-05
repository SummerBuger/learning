package com.liam.learn.threadcontext;

import java.util.HashMap;
import java.util.Map;

public class ThreadContext {

  private static final ThreadLocal<Map<String, Object>> CTX = new ThreadLocal<>();

  public ThreadContext() {
  }

  public static void ensureInited() {
    if (CTX.get() == null) {
      init();
    }

  }

  public static void clean() {
    CTX.remove();
  }

  public static boolean init() {
    if (CTX.get() != null) {
      return false;
    } else {
      Map<String, Object> currentThreadCtx = new HashMap(16);
      CTX.set(currentThreadCtx);
      return true;
    }
  }

  public static void put(String key, Object value) {
    try {
       CTX.get().put(key, value);
    } catch (NullPointerException var3) {
      System.out.println(
        "调用ThreadContext时，必须要先进行ThreadContext的init，线程退出前再进行clean，避免被其他线程使用到本线程的数据，发生线程安全问题。");
      throw var3;
    }
  }

  public static Object get(String key) {
    Map<String, Object> map = CTX.get();
    return map == null ? null : CTX.get().get(key);
  }

}
