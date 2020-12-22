package com.huihui.aligo.io.tank.netty;

import com.huihui.aligo.io.tank.message.BaseStateMessage;
import com.huihui.aligo.io.tank.message.StateMessageDecoder;
import com.huihui.aligo.io.tank.message.StateMessageEncoder;
import com.huihui.aligo.io.tank.message.TankJoinMessage;
import com.huihui.aligo.io.tank.ui.NettyTankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.Setter;

/**
 * 封装一个netty的客户端
 * 这是坦克大战的client
 * @author minghui.y
 * @create 2020-12-21 11:35 上午
 **/
@Getter
@Setter
public class NettyClient {

    private static NettyClient INSTANCE = new NettyClient();

    public static NettyClient getInstance() {
        return INSTANCE;
    }

    //EventLoopGroup：执行任务的线程组，继承自juc.ScheduledExecutorService
    private EventLoopGroup group;
    //辅助启动类
    private Bootstrap bootstrap;
    /**
     * 该channel是与服务端连接的channel，用于写数据
     */
    private Channel channel;


    private NettyClient() {
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
                           //先添加编码器&解码器，在添加处理器
                           //所有的数据输出都会经过走编码器！
                           channelPipeline.addLast( new StateMessageEncoder() )
                                   .addLast( new StateMessageDecoder() )
                                   .addLast( new SimpleTankClientHandler() );
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
     * @param message
     */
    public void send(BaseStateMessage message) {
        channel.writeAndFlush( message );
    }




    /**
     * 第二版处理器
     * 适用于处理一种消息类型
     */
    public static class SimpleTankClientHandler extends SimpleChannelInboundHandler<BaseStateMessage> {

        @Override
        public void channelActive( ChannelHandlerContext ctx ) throws Exception {
            //通道可用时，向服务器写入自己坦克的信息
            //写入坦克join消息
            ctx.writeAndFlush( new TankJoinMessage( NettyTankFrame.getInstance().getMainTank() ) );
        }

        @Override
        protected void channelRead0( ChannelHandlerContext ctx, BaseStateMessage msg ) throws Exception {
            //读取服务端的数据
            System.out.println("客户端接收：" + msg);
            //由消息自己处理
           msg.handle( ctx );
        }
    }

}
