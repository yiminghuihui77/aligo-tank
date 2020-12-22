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
 * 坦克改变方向message
 *
 * @author minghui.y
 * @create 2020-12-22 3:58 下午
 **/
public class TankDirChangeMessage extends BaseStateMessage {

    private UUID uuid;
    private Dir dir;
    private int x;
    private int y;

    public TankDirChangeMessage(){}
    public TankDirChangeMessage( NettyTank tank ) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.uuid = tank.getUuid();
    }

    public TankDirChangeMessage( UUID uuid, Dir dir, int x, int y ) {
        this.uuid = uuid;
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    @Override
    public void handle( ChannelHandlerContext ctx ) {
        if (this.uuid.equals( NettyTankFrame.getInstance().getMainTank().getUuid() )) {
            return;
        }
        NettyTank tank = NettyTankFrame.getInstance().getBadTank(this.uuid.toString());
        if (tank != null) {
            tank.setX( x );
            tank.setY( y );
            tank.setDir( dir );
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
        return MessageType.TANK_DIR_CHANGE;
    }
}
