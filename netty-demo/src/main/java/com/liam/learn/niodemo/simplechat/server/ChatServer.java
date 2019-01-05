package com.liam.learn.niodemo.simplechat.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chaochun.ccc on 2017-07-17.
 */
public class ChatServer {

  public static void main(String[] args) {
    new ChatServer(8080).run();
  }

  private static final int BLAK_LOG_SIZE = 1024;

  private Selector selector;

  private ServerSocketChannel serverSocketChannel;

  private volatile boolean stop;

  public ChatServer(int port) {
    try {
      selector = Selector.open();
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      serverSocketChannel.socket().bind(new InetSocketAddress(port), BLAK_LOG_SIZE);
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("the chat server is start in port: " + port);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void stop() {
    this.stop = true;
  }

  public void run() {
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

  private void handleInput(SelectionKey selectionKey) throws IOException {
    if (selectionKey.isValid()) {
      if (selectionKey.isAcceptable()) {
        ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
      }
      if (selectionKey.isReadable()) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        if (read > 0) {
          byteBuffer.flip();
          byte[] bytes = new byte[byteBuffer.remaining()];
          byteBuffer.get(bytes);
          String data = new String(bytes, "UTF-8");
          System.out.println("the chat server received msg: " + data);
          String response = "the chat received the msg at " + new Date().toString();
          doWriteResponse(socketChannel, response);
        } else if (read < 0) {
          selectionKey.cancel();
          socketChannel.close();
        }
      }
    }
  }

  private void doWriteResponse(SocketChannel socketChannel, String response) throws IOException {
    if (response != null && response.trim().length() > 0) {
      byte[] bytes = response.getBytes();
      ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
      byteBuffer.put(bytes);
      byteBuffer.flip();
      System.out.println("send msg to client, msg: " + response);
      socketChannel.write(byteBuffer);
    }
  }
}
