package com.base.liam;

import java.util.StringTokenizer;

public class StringTokenDemo {

  public static void main(String[] args) {
    String s = "1catalina.org.apache.juli.FileHandler, 2localhost.org.apache.juli.FileHandler, 3manager.org.apache.juli.FileHandler, 4host-manager.org.apache.juli.FileHandler, java.util.logging.ConsoleHandler";
    StringTokenizer tok = new StringTokenizer(s, ",");
    while (tok.hasMoreTokens()) {
      String handlerName = (tok.nextToken().trim());
      System.out.println("handlerName = (tok.nextToken().trim()): " + handlerName);
      String handlerClassName = handlerName;
      System.out.println("handlerClassName = handlerName: " + handlerClassName);
      String prefix = "";
      if (handlerClassName.length() <= 0) {
        continue;
      }
      if (Character.isDigit(handlerClassName.charAt(0))) {
        int pos = handlerClassName.indexOf('.');
        if (pos >= 0) {
          prefix = handlerClassName.substring(0, pos + 1);
          handlerClassName = handlerClassName.substring(pos + 1);
        }
      }
      System.out.println("handlerClassName.substring(0, pos + 1);: " + prefix);
      System.out.println("handlerClassName = handlerClassName.substring(pos + 1);: " + handlerClassName);

    }
  }

}
