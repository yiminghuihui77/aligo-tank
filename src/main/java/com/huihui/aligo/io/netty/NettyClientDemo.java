package com.huihui.aligo.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty客户端demo
 * 注意：netty所有方法都是异步的，执行结果通过添加监听器处理
 * @author minghui.y
 * @create 2020-12-20 7:14 下午
 **/
public class NettyClientDemo {

    public static void main( String[] args ) {

        //EventLoopGroup：执行任务的线程组，继承自juc.ScheduledExecutorService
        EventLoopGroup group = new NioEventLoopGroup();
        //辅助启动类
        Bootstrap bootstrap = new Bootstrap();

       /* try {
            //写法一：
            bootstrap.group(group)
                    .channel( NioSocketChannel.class )
                    .handler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel( SocketChannel socketChannel ) throws Exception {
                            System.out.println(socketChannel);
                        }
                    } ).connect("localhost", 8890)
                    //netty所有方法都是异步的，sync()表示同步，阻塞直到操作完成为止（无论是否连接成功）
                    .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        //写法二：
        ChannelFuture future = bootstrap.group(group)
                .channel( NioSocketChannel.class )
                .handler( new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel( SocketChannel socketChannel ) throws Exception {
                        System.out.println(socketChannel);
                    }
                } )
                //connect()方法是异步的，不管是否连接成功，线程继续向下执行
                .connect("localhost", 8890);

        //注册监听器
        future.addListener( new ChannelFutureListener() {
            @Override
            public void operationComplete( ChannelFuture channelFuture ) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("netty客户端连接成功...");
                } else {
                    System.out.println("netty客户端连接失败！");
                }
            }
        } );

    }

}
