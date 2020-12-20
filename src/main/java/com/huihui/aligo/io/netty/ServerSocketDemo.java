package com.huihui.aligo.io.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

/**
 * @author minghui.y
 * @create 2020-12-20 7:20 下午
 **/
public class ServerSocketDemo {

    public static void main( String[] args ) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind( new InetSocketAddress( "localhost", 8890 ) );
            serverSocket.accept();

            System.out.println("服务端接收请求...");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
