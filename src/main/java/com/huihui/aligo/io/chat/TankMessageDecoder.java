package com.huihui.aligo.io.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 坦克message的解码器
 *
 * @author minghui.y
 * @create 2020-12-21 2:19 下午
 **/
public class TankMessageDecoder extends ByteToMessageDecoder {

    /**
     * 解码器
     * 将ByteBuf中的数据封装成TankMessage
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception {
        if (in.readableBytes() < 8) {
            //未满8字节，直接返回，避免TCP拆包、粘包问题
            return;
        }
        int x = in.readInt();
        int y = in.readInt();
        out.add( new TankMessage( x, y ) );

    }
}
