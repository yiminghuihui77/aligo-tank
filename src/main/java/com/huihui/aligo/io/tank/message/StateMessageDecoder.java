package com.huihui.aligo.io.tank.message;

import com.huihui.aligo.io.tank.constant.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 通用的消息解码器
 * 将ByteBuf解码为对应的message
 * 解码时特别注意：TCP数据传输中涉及到拆包和粘包，因此需要判断数据是否传输到位
 * @author minghui.y
 * @create 2020-12-22 11:08 上午
 **/
public class StateMessageDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception {
        if (in.readableBytes() < 8) {
            //可读数据长度不足8字节，直接返回
            return;
        }
        //标记当前读指针位置
        in.markReaderIndex();

        MessageType messageType = MessageType.values()[in.readInt() ];
        int length = in.readInt();

        if (in.readableBytes() < length) {
            //重置读指针位置到上次标记的位置
            in.resetReaderIndex();
            return;
        }
        //读取数据到数组中
        byte[] buffer = new byte[length];
        in.readBytes( buffer );

        BaseStateMessage message = null;

        /**
         * 每新增一种消息，羡慕代码就增加一种判断
         * 可以通过反射是实例化：
         * Class.forName(报名 + messageType).newInstance();
         */
        switch (messageType) {
            case TANK_JOIN:
                //坦克加入
                message = new TankJoinMessage();
                break;
            case TANK_MOVING_CHANGE:
                //改变运动状态
                message = new TankMovingChangeMessage();
                break;
            case TANK_DIR_CHANGE:
                //改变方向
                message = new TankDirChangeMessage();
                break;
            case BULLET_JOIN:
                message = new BulletJoinMessage();
            default:
                break;
        }

        //将字节数组的数据转为message中的属性
        message.parse( buffer );
        out.add( message );
    }
}
