package com.huihui.aligo.io.tank;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * netty服务端
 * 现在的服务器，座位坦克大战的服务器
 * 当服务器接收到来自客户端的数据，仅将数据转发到其他channel
 * @author minghui.y
 * @create 2020-12-21 2:02 下午
 **/
public class NettyServer {

    //通道组，用于存储channel
    public static ChannelGroup clients = new DefaultChannelGroup( GlobalEventExecutor.INSTANCE );

    public static void main( String[] args ) {
        //处理客户端的连接（餐馆的迎宾）
        EventLoopGroup bossGroup = null;
        //处理连接后的任务（餐馆的服务员）
        EventLoopGroup workerGroup = null;

        try {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup =  new NioEventLoopGroup(2);

            ServerBootstrap bootstrap = new ServerBootstrap();

            ChannelFuture future = bootstrap.group(bossGroup, workerGroup)
                    .channel( NioServerSocketChannel.class )
                    .childHandler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel( SocketChannel socketChannel ) throws Exception {
//                            System.out.println(socketChannel);
                            //添加处理器
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            //先添加解码器，再添加处理器
                            channelPipeline.addLast( new TankStateDecoder() )
                                    .addLast( new TankStateEncoder() )
                                    .addLast(new TankServerChildHandler());
                        }
                    } )
                    .bind(8890)
                    //sync()阻塞，直到bind(8890)结果出来为止
                    .sync();

            System.out.println("netty server started !");
            //阻塞，close()方法被调用时，阻塞才结束
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
        }

    }


    public static class TankServerChildHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive( ChannelHandlerContext ctx ) throws Exception {
            //channel可用
            System.out.println(Thread.currentThread().getName() + "：channel已经可用");
            //添加当前通道
            clients.add( ctx.channel() );
        }

        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
            //服务端读取到数据，仅做转发到其他channel
            System.out.println("server接收数据" + msg);
            clients.writeAndFlush( msg );

        }

        @Override
        public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception {
            //通道产生异常，捕获异常后，关闭
            cause.printStackTrace();
            //移出异常的channel
            clients.remove( ctx.channel() );
            ctx.close();
        }
    }
}
