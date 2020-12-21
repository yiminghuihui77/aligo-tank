package com.huihui.aligo.io.netty;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;
import java.util.concurrent.TimeUnit;


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
//                        System.out.println(socketChannel);
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        //添加处理器
                        channelPipeline.addLast( new clientHandler() );

                    }
                } )
                //connect()方法是异步的，不管是否连接成功，线程继续向下执行
                .connect("localhost", 8890);

        //注册监听器
        future.addListener( new ChannelFutureListener() {
            @Override
            public void operationComplete( ChannelFuture channelFuture ) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println(Thread.currentThread().getName() + " : netty客户端连接成功...");
                } else {
                    System.out.println(Thread.currentThread().getName() + " : netty客户端连接失败！");
                }
            }
        } );

    }


    /**
     * 客户端的channel处理器
     */
     static class clientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive( ChannelHandlerContext ctx ) throws Exception {
            //当channel第一个连上可用，向服务端写入hello
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes());
            ctx.writeAndFlush( byteBuf );
        }

        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
            //客户端读取来自服务端的数据
            ByteBuf byteBuf = null;
            try {
                byteBuf = (ByteBuf) msg;
                //获取数据,数组容量为可读数量
                byte[] buffer = new byte[byteBuf.readableBytes()];
                byteBuf.getBytes( byteBuf.readerIndex(), buffer );
                System.out.println(Thread.currentThread().getName() + ": 客户端读取数据：" + new String(buffer));
                //获取byteBuf的引用数量
                System.out.println(byteBuf.refCnt());

                //读取数据后再次写入
                ctx.writeAndFlush( Unpooled.copiedBuffer( "client copy!".getBytes() ) );
                TimeUnit.SECONDS.sleep( 1 );

            } finally {
                if (byteBuf != null) {
                    ReferenceCountUtil.release( byteBuf );
                }
                System.out.println(Thread.currentThread().getName() + " : byteBuf引用数量：" + byteBuf.refCnt());
            }

        }
    }

}
