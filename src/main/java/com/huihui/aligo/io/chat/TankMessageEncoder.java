package com.huihui.aligo.io.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 坦克信息的编码器
 *
 * @author minghui.y
 * @create 2020-12-21 2:04 下午
 **/
public class TankMessageEncoder extends MessageToByteEncoder<TankMessage> {

    /**
     * 编码器
     * 将TankMessage信息写入ByteBuf
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode( ChannelHandlerContext ctx, TankMessage msg, ByteBuf out ) throws Exception {
        out.writeInt( msg.getX() );
        out.writeInt( msg.getY() );
    }
}
