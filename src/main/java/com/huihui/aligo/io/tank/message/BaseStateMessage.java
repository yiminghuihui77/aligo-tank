package com.huihui.aligo.io.tank.message;

import com.huihui.aligo.io.tank.constant.MessageType;
import io.netty.channel.ChannelHandlerContext;

/**
 * netty数据传输model的基类
 *
 * @author minghui.y
 * @create 2020-12-21 11:06 下午
 **/
public abstract class BaseStateMessage {

    /**
     * 客户端读取服务端数据时调用
     * 消息本身来处理自己
     * @param ctx
     */
    public abstract void handle( ChannelHandlerContext ctx);

    /**
     * message中的属性，按照指定的协议，转为字节数组
     * @return
     */
    public abstract byte[] toBytes();

    /**
     * 字节数组中的数据，按照指定的协议读取，并为message的属性赋值
     * @param bytes
     */
    public abstract void parse(byte[] bytes);

    /**
     * 获取当前消息的消息类型
     * @return
     */
    public abstract MessageType getType();


}
