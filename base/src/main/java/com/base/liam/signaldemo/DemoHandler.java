package com.base.liam.signaldemo;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Created by chaochun.ccc on 2017-05-24.
 */
public class DemoHandler implements SignalHandler {
  public void handle(Signal signal) {
    System.out.println("The signal " + signal.getName() + " has been received......");
  }
}
