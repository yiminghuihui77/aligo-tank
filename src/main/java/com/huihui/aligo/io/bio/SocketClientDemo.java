package com.huihui.aligo.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * socket的客户端案例
 *
 * @author minghui.y
 * @create 2020-12-19 10:13 下午
 **/
public class SocketClientDemo {

    public static void main( String[] args ) {

        Socket socket = null;
        try {
            //连接服务端也是阻塞的
             socket = new Socket("127.0.0.1", 8890);
             //写入，数据读写也是阻塞的
             socket.getOutputStream().write( "hello i am client".getBytes() );
             socket.getOutputStream().flush();
            System.out.println("客户端发送数据完毕");

            while (true) {
                byte[] buffer = new byte[1024];
                int length = socket.getInputStream().read(buffer);
                System.out.println("客户端接收：" + new String(buffer, 0, length));
                socket.getOutputStream().write( "client has received data.".getBytes() );
                socket.getOutputStream().flush();
                TimeUnit.SECONDS.sleep( 1 );

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
