package com.huihui.aligo.io.tank.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 通用的编码器
 *
 * 将message转换成ByteBuf
 * 数据传输协议： 消息类型 + 数据长度 + 字节数组
 * @author minghui.y
 * @create 2020-12-22 10:56 上午
 **/
public class StateMessageEncoder extends MessageToByteEncoder<BaseStateMessage> {


    @Override
    protected void encode( ChannelHandlerContext ctx, BaseStateMessage msg, ByteBuf out ) throws Exception {
        //消息类型
        out.writeInt( msg.getType().ordinal() );
        //字节数组长度
        out.writeInt( msg.toBytes().length );
        //字节数组
        out.writeBytes( msg.toBytes() );
    }
}
