package com.huihui.aligo.io.tank;

import com.huihui.aligo.io.tank.netty.NettyClient;
import com.huihui.aligo.io.tank.ui.NettyTankFrame;

/**
 * netty版坦克大战启动类
 * @author minghui.y
 * @create 2020-12-21 5:57 下午
 **/
public class HelloNettyTank {

    public static void main( String[] args ) {
        NettyTankFrame frame= NettyTankFrame.getInstance();

        new Thread(() -> {
            while (true) {
                try {
                    frame.repaint();
                    Thread.sleep( 200 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        NettyClient client = NettyClient.getInstance();
        //传入server服务器启动的主机IP地址
        client.connect("192.168.31.90");
    }
}
