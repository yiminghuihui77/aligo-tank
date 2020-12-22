package com.huihui.aligo.io.tank.message;

import com.huihui.aligo.io.tank.constant.MessageType;
import com.huihui.aligo.io.tank.ui.NettyBullet;
import com.huihui.aligo.io.tank.ui.NettyTankFrame;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.UUID;

/**
 * 子弹加入消息
 * @author minghui.y
 * @create 2020-12-22 5:12 下午
 **/
public class BulletJoinMessage extends BaseStateMessage {

    private int x;
    private int y;
    private Dir dir;
    private Group group;
    private UUID uuid;

    public BulletJoinMessage(){}

    public BulletJoinMessage( int x, int y, Dir dir, Group group, UUID uuid ) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.uuid = uuid;
    }

    public BulletJoinMessage( NettyBullet bullet ) {
        this.x = bullet.getX();
        this.y = bullet.getY();
        this.dir = bullet.getDir();
        this.group = bullet.getGroup();
        this.uuid = bullet.getUuid();
    }

    @Override
    public void handle( ChannelHandlerContext ctx ) {
        //已经存在，则跳过
        if (NettyTankFrame.getInstance().getBulletMap().get( this.uuid.toString() ) != null) {
            return;
        }

        //创建新的子弹
        NettyTankFrame.getInstance().getBulletMap().put( this.uuid.toString(),  new NettyBullet( x, y, dir, group, uuid ));
    }

    public byte[] toBytes() {
        ByteArrayOutputStream bos = null;
        DataOutputStream oos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new DataOutputStream( bos );
            oos.writeInt( x );
            oos.writeInt( y );
            oos.writeInt( dir.ordinal() );
            oos.writeInt( group.ordinal() );
            oos.writeLong( uuid.getMostSignificantBits() );
            oos.writeLong( uuid.getLeastSignificantBits() );

            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void parse( byte[] bytes ) {
        ByteArrayInputStream bis = null;
        DataInputStream dis = null;

        try {
            bis = new ByteArrayInputStream( bytes );
            dis = new DataInputStream( bis );
            //读取数据
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.group = Group.values()[dis.readInt()];
            this.uuid = new UUID( dis.readLong(), dis.readLong() );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MessageType getType() {
        return MessageType.BULLET_JOIN;
    }

    @Override
    public String toString() {
        return "BulletJoinMessage{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", uuid=" + uuid +
                '}';
    }
}
