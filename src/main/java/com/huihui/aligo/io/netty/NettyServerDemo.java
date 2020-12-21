package com.huihui.aligo.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * netty的服务端
 * 功能：实现服务端与客户端通信，客户端发送的数据，服务端向多个channel发送数据
 * @author minghui.y
 * @create 2020-12-20 8:09 下午
 **/
public class NettyServerDemo {

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
                            channelPipeline.addLast(new ServerChildHandler());
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
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    public static class ServerChildHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive( ChannelHandlerContext ctx ) throws Exception {
            //channel可用
            System.out.println(Thread.currentThread().getName() + "：channel已经可用");
            //添加当前通道
            NettyServerDemo.clients.add( ctx.channel() );
        }

        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
            ByteBuf byteBuf = null;
            try {
                byteBuf = (ByteBuf) msg;
                byte[] buffer = new byte[byteBuf.readableBytes()];
                byteBuf.getBytes( byteBuf.readerIndex(), buffer );
                System.out.println(Thread.currentThread().getName() + "服务端读取数据：" + new String(buffer));

//                ctx.writeAndFlush( Unpooled.copiedBuffer( "server copy!".getBytes() ) );
                //向所有通道写入数据？
                NettyServerDemo.clients.writeAndFlush( msg );

            } finally {
//                if (byteBuf != null) {
//                    ReferenceCountUtil.release( byteBuf );
//                }
//                System.out.println(Thread.currentThread().getName() + "byteBuf引用数量：" + byteBuf.refCnt());
            }
        }

        @Override
        public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception {
            //通道产生异常，捕获异常后，关闭
            cause.printStackTrace();
            ctx.close();
        }
    }
}
