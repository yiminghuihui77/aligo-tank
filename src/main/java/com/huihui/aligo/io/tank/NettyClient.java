package com.huihui.aligo.io.tank;

import com.huihui.aligo.io.tank.ui.NettyTank;
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

    //EventLoopGroup：执行任务的线程组，继承自juc.ScheduledExecutorService
    private EventLoopGroup group;
    //辅助启动类
    private Bootstrap bootstrap;
    /**
     * 该channel是与服务端连接的channel，用于写数据
     */
    private Channel channel;


    public NettyClient() {
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
                           channelPipeline.addLast( new TankStateEncoder() )
                                   .addLast( new TankStateDecoder() )
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
     * 第二版处理器
     * 适用于处理一种消息类型
     */
    public static class SimpleTankClientHandler extends SimpleChannelInboundHandler<TankStateMessage> {

        @Override
        public void channelActive( ChannelHandlerContext ctx ) throws Exception {
            //通道可用时，向服务器写入自己坦克的信息
            ctx.writeAndFlush( new TankStateMessage( NettyTankFrame.getInstance().getMainTank() ) );
        }

        @Override
        protected void channelRead0( ChannelHandlerContext ctx, TankStateMessage msg ) throws Exception {
            //读取服务端的数据
            System.out.println("客户端接收：" + msg);
            //服务端转发的是自己的坦克，或者是已有的地方坦克，跳过
            if (msg.getUuid().toString().equals( NettyTankFrame.getInstance().getMainTank().getUuid().toString() ) ||
            NettyTankFrame.getInstance().getBadTank( msg.getUuid().toString() ) != null) {
                return;
            }

            //新的玩家（坦克）加入，在自己的list中添加
            NettyTank newTank = new NettyTank( msg );
            NettyTankFrame.getInstance().addBadTank( newTank );

            //再次将自己的坦克传到服务端，让新的坦克知道自己
            ctx.writeAndFlush( new TankStateMessage( NettyTankFrame.getInstance().getMainTank() ) );
        }
    }

}
