package com.huihui.aligo.io.tank;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author minghui.y
 * @create 2020-12-21 3:45 下午
 **/
public class TankStateEncoder extends MessageToByteEncoder<TankStateMessage> {


    @Override
    protected void encode( ChannelHandlerContext ctx, TankStateMessage msg, ByteBuf out ) throws Exception {
        //将TankStateMessage写入ByteBuf
        out.writeInt( msg.getX() );
        out.writeInt( msg.getY() );
        //吸入枚举顺序
        out.writeInt( msg.getDir().ordinal() );
        out.writeInt( msg.getGroup().ordinal() );
        out.writeBoolean( msg.isMoving());
        //uuid供128位16字节，分别写入高8位和低8位
        out.writeLong( msg.getUuid().getMostSignificantBits() );
        out.writeLong( msg.getUuid().getLeastSignificantBits() );
    }
}
