package com.liam.learn;

import java.io.IOException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class App implements Watcher {

  public static final String ZK_10_ADDR = "10.50.72.142:2181,10.50.72.143:2181,10.50.72.144:2181,10.50.72.145:2181,10.50.72.80:2181";

  public static final String ZK_11_ADDR = "11.7.99.78:2181,11.7.99.79:2181,11.7.99.80:2181,11.7.99.81:2181,11.7.99.48:2181";

  public static void main(String[] args) {


  }

  private ZooKeeper zooKeeper;

  public void startZk() throws IOException {
    zooKeeper = new ZooKeeper(ZK_11_ADDR, 5000, this);
  }

  public void stopZk() throws InterruptedException {
    zooKeeper.close();
  }

  @Override
  public void process(WatchedEvent watchedEvent) {
    System.out.println(watchedEvent);
  }
}
