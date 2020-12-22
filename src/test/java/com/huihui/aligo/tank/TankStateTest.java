package com.huihui.aligo.tank;

import com.huihui.aligo.io.tank.message.TankStateDecoder;
import com.huihui.aligo.io.tank.message.TankStateEncoder;
import com.huihui.aligo.io.tank.message.TankJoinMessage;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * 测试对TankState的编码与解码
 *
 * @author minghui.y
 * @create 2020-12-21 4:00 下午
 **/
public class TankStateTest {

    /**
     * 测试编码
     */
    @Test
    public void testEncode() {

        TankJoinMessage message = new TankJoinMessage(10, 20 , Dir.UP, Group.BAD, true, UUID.randomUUID() );

        EmbeddedChannel channel = new EmbeddedChannel(new TankStateEncoder() );
        channel.writeOutbound( message );

        //获取数据
        ByteBuf byteBuf = channel.readOutbound();
        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        Dir dir = Dir.values()[byteBuf.readInt()];
        Group group = Group.values()[byteBuf.readInt()];
        boolean moving = byteBuf.readBoolean();
        UUID uuid = new UUID( byteBuf.readLong(), byteBuf.readLong());

        //验证数据
        Assert.assertEquals( 10, x );
        Assert.assertEquals( 20, y );
        Assert.assertEquals( message.getDir(), dir );
        Assert.assertEquals( message.getGroup(), group);
        Assert.assertEquals( message.isMoving(), moving );
        Assert.assertEquals( message.getUuid(), uuid );

    }


    @Test
    public void testDecode() {

        TankJoinMessage message = new TankJoinMessage(10, 20 , Dir.UP, Group.BAD, true, UUID.randomUUID() );
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast( new TankStateDecoder() );

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes( message.toBytes() );

        channel.writeInbound( byteBuf.duplicate() );
        //读取解码后的结果
        TankJoinMessage message2 = channel.readInbound();

        //验证
        Assert.assertEquals( message.getX(), message2.getX() );
        Assert.assertEquals( message.getY(), message2.getY() );
        Assert.assertEquals( message.getDir(), message2.getDir() );
        Assert.assertEquals( message.getGroup(), message2.getGroup() );
        Assert.assertEquals( message.isMoving(), message2.isMoving() );
        Assert.assertEquals( message.getUuid(), message2.getUuid());


    }


}
