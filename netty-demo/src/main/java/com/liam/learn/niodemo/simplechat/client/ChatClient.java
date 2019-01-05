package com.liam.learn.niodemo.simplechat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chaochun.ccc on 2017-07-17.
 */
public class ChatClient {

  public static void main(String[] args) {
    new ChatClient("localhost", 8080).run();
  }

  private String host;

  private int port;

  private Selector selector;

  private SocketChannel socketChannel;

  private volatile boolean stop;

  public ChatClient(String host, int port) {
    this.host = host;
    this.port = port;
    try {
      selector = Selector.open();
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
      System.out.println("the client begin to connect to port: " + port + ", host: " + host);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    this.stop = true;
  }

  public void run() {
    try {
      doConnect();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    while (!stop) {
      try {
        selector.select(1000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        SelectionKey selectionKey;
        while (iterator.hasNext()) {
          selectionKey = iterator.next();
          iterator.remove();
          try {
            handleInput(selectionKey);
          } catch (Exception e) {
            if (selectionKey != null) {
              selectionKey.cancel();
              if (selectionKey.channel() != null) {
                selectionKey.channel().close();
              }
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    if (selector != null) {
      try {
        selector.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void handleInput(SelectionKey key) throws IOException {
    if (key.isValid()) {
      SocketChannel channel = (SocketChannel) key.channel();
      if (key.isConnectable()) {
        if(socketChannel.finishConnect()) {
          channel.register(selector, SelectionKey.OP_READ);
          doWrite(channel);
        } else {
          System.out.println("the client exit");
          System.exit(1);
        }
      }
      if (key.isReadable()){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        if (read > 0) {
          byteBuffer.flip();
          byte[] bytes = new byte[byteBuffer.remaining()];
          byteBuffer.get(bytes);
          String msg = new String(bytes, "UTF-8");
          System.out.println("get the msg from chat server: " + msg);
          this.stop = true;
        } else if (read < 0) {
          key.cancel();
          socketChannel.close();
        }
      }
    }
  }

  private void doConnect() throws IOException {

    boolean connect = socketChannel.connect(new InetSocketAddress(host, port));
    if (connect) {
      socketChannel.register(selector, SelectionKey.OP_READ);
      doWrite(socketChannel);
    } else {
      socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

  }

  private void doWrite(SocketChannel socketChannel) throws IOException {
    String msg = "the client send the msg at: " + new Date().toString();
    byte[] bytes = msg.getBytes();
    ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
    byteBuffer.put(bytes);
    byteBuffer.flip();
    socketChannel.write(byteBuffer);
    if (!byteBuffer.hasRemaining()) {
      System.out.println("send msg to server finished! msg: " + msg);
    }
  }
}
