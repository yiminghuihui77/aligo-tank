package com.huihui.aligo.io.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

/**
 * 封装一个netty的客户端
 * 与NettyServerDemo服务端交互
 * @author minghui.y
 * @create 2020-12-21 11:35 上午
 **/
@Getter
@Setter
public class NettyClient {

    private TextField tf;
    private TextArea ta;
    //EventLoopGroup：执行任务的线程组，继承自juc.ScheduledExecutorService
    private EventLoopGroup group;
    //辅助启动类
    private Bootstrap bootstrap;
    /**
     * 该channel是与服务端连接的channel，用于写数据
     */
    private Channel channel;


    public NettyClient(TextField tf, TextArea ta) {
        this.tf = tf;
        this.ta = ta;
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
    }

    /**
     * 客户端与服务端连接
     */
    public void connect() {
       try {
           bootstrap.group(group)
                   .channel( NioSocketChannel.class )
                   .handler( new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel( SocketChannel ch ) throws Exception {
                           //添加处理器，ChannelPipeline可以理解为channel的责任链条
                           ChannelPipeline channelPipeline = ch.pipeline();
                           channelPipeline.addLast( new ChatClientInboundHandler() );
                       }
                   } )
                   .connect("localhost", 8890)
                   .addListener( new ChannelFutureListener() {
                       @Override
                       public void operationComplete( ChannelFuture future ) throws Exception {
                           if (future.isSuccess()) {
                               System.out.println(Thread.currentThread().getName() + " : netty客户端连接成功...");
                               //channel初始化
                               channel = future.channel();
                           } else {
                               System.out.println(Thread.currentThread().getName() + " : netty客户端连接失败！");
                           }
                       }
                   } )
                   .sync();


       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    /**
     * 向服务端发送数据
     * @param source
     */
    public void send2Server(String source) {
        if (StringUtils.isEmpty( source )) {
            return;
        }
        channel.writeAndFlush( Unpooled.copiedBuffer( source.getBytes() ) );
    }


    public  class ChatClientInboundHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive( ChannelHandlerContext ctx ) throws Exception {
            //ChannelHandlerContext可以理解为channel的上下文
            //通道可用，向服务端写入hello
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes());
            ctx.writeAndFlush( byteBuf );
        }

        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
            //通道可读
            ByteBuf byteBuf = (ByteBuf) msg;
            byte[] data = new byte[byteBuf.readableBytes()];
            byteBuf.getBytes( byteBuf.readerIndex(), data);
            System.out.println("客户端读取数据：" + new String(data));
            //在显示框中显示来自服务端的数据
            ta.setText( ta.getText() + "\nserver:" + new String(data) );
        }
    }



    public static class ChatClientOutboundHandler extends ChannelOutboundHandlerAdapter {
    }

}
