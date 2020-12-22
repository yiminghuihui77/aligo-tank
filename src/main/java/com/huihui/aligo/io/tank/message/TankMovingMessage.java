package com.huihui.aligo.io.tank.message;

import com.huihui.aligo.io.tank.constant.MessageType;
import com.huihui.aligo.io.tank.ui.NettyTank;
import com.huihui.aligo.io.tank.ui.NettyTankFrame;
import com.huihui.aligo.tank.constant.Dir;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.UUID;

/**
 * 坦克移动message
 *
 * @author minghui.y
 * @create 2020-12-22 11:56 上午
 **/
public class TankMovingMessage extends BaseStateMessage {

    private UUID uuid;
    private int x;
    private int y;
    private Dir dir;

    public TankMovingMessage(){}

    public TankMovingMessage( UUID uuid, int x, int y, Dir dir ) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public TankMovingMessage(NettyTank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.uuid = tank.getUuid();
    }

    @Override
    public void handle( ChannelHandlerContext ctx ) {
        //如果是当前客户端的主战坦克，则忽略
        if (this.uuid.equals( NettyTankFrame.getInstance().getMainTank().getUuid() )) {
            return;
        }
        //移动敌方坦克
        NettyTank tank = NettyTankFrame.getInstance().getBadTank( this.uuid.toString() );
        if (tank != null) {
            tank.setMoving( true );
            tank.setX( this.x );
            tank.setY( this.y );
            tank.setDir( this.dir );
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream bos = null;
        DataOutputStream oos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new DataOutputStream( bos );

            oos.writeInt( x );
            oos.writeInt( y );
            oos.writeInt( dir.ordinal() );
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
        return MessageType.TANK_MOVING;
    }

    @Override
    public String toString() {
        return "TankMovingMessage{" +
                "uuid=" + uuid +
                ", x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }
}
