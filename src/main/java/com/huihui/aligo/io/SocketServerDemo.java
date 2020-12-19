package com.huihui.aligo.io;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单的Socket案例
 * 服务端先启动，否则客户端连接报错
 * @author minghui.y
 * @create 2020-12-19 8:22 下午
 **/
public class SocketServerDemo {

    public static void main( String[] args ) {

        ServerSocket ss = null;
        try {
            ss = new ServerSocket();
            ss.bind( new InetSocketAddress( "127.0.0.1", 8890 ));

            while (true) {
                //accept()方法，服务器阻塞，直到客户端请求
                Socket s = ss.accept();

                //另起线程处理
                new Thread(() -> handle( s )).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handle(Socket socket){
       try {
          while (true) {
              byte[] buffer = new byte[1024];
              //读取
              int length = socket.getInputStream().read(buffer);
              System.out.println("server 端接收到：" + new String(buffer, 0, length));

              //写入
              socket.getOutputStream().write( "i am server".getBytes());
              socket.getOutputStream().flush();
          }

       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
               socket.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }


    }
}
