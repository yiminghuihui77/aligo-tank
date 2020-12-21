package com.huihui.aligo.io.tank;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @author minghui.y
 * @create 2020-12-21 3:53 下午
 **/
public class TankStateDecoder extends ByteToMessageDecoder {

    /**
     * 将ByteBuf中的数据封装为TankState
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception {
        if (in.readableBytes() < 33) {
            //ByteBuf中的数据不足，可能是拆包所致，需要等到数据到齐才处理
            return;
        }
        TankStateMessage message = new TankStateMessage();
        int x = in.readInt();
        int y = in.readInt();
        int dirOrigin = in.readInt();
        int groupOrigin = in.readInt();
        boolean moving = in.readBoolean();
        long high = in.readLong();
        long low = in.readLong();

        message.setX( x );
        message.setY( y );
        message.setDir( Dir.values()[dirOrigin] );
        message.setGroup( Group.values()[groupOrigin] );
        message.setMoving( moving );
        message.setUuid( new UUID( high, low ) );

        out.add( message );
    }
}
