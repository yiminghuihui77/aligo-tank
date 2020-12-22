package com.huihui.aligo.io.tank.message;

import com.huihui.aligo.io.tank.constant.MessageType;
import com.huihui.aligo.io.tank.ui.NettyTank;
import com.huihui.aligo.io.tank.ui.NettyTankFrame;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.UUID;

/**
 * 表示坦克状态的数据传输
 * @author minghui.y
 * @create 2020-12-21 3:40 下午
 **/
@Getter
@Setter
public class TankStateMessage extends BaseStateMessage {

    private int x;
    private int y;
    private Dir dir;
    private Group group;
    private boolean moving;
    private UUID uuid;


    public TankStateMessage( int x, int y, Dir dir, Group group, boolean moving, UUID uuid ) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;
        this.uuid = uuid;
    }
    public TankStateMessage(){}

    public TankStateMessage( NettyTank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.moving = tank.isMoving();
        this.uuid = tank.getUuid();
    }


    @Override
    public void handle( ChannelHandlerContext ctx) {
        //读取服务端的数据
        System.out.println("客户端接收：" + this);
        //服务端转发的是自己的坦克，或者是已有的地方坦克，跳过
        if (this.getUuid().toString().equals( NettyTankFrame.getInstance().getMainTank().getUuid().toString() ) ||
                NettyTankFrame.getInstance().getBadTank( this.getUuid().toString() ) != null) {
            return;
        }

        //新的玩家（坦克）加入，在自己的list中添加
        NettyTank newTank = new NettyTank( this );
        NettyTankFrame.getInstance().addBadTank( newTank );

        //再次将自己的坦克传到服务端，让新的坦克知道自己
        ctx.writeAndFlush( new TankStateMessage( NettyTankFrame.getInstance().getMainTank() ) );
    }

    /**
     * 将对象转为byte[]
     * @return
     */
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
            oos.writeInt( group.ordinal() );
            oos.writeBoolean( moving );
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
            this.moving = dis.readBoolean();
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
        return MessageType.TANK_JOIN;
    }


    @Override
    public String toString() {
        return "TankStateMessage{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", moving=" + moving +
                ", uuid=" + uuid +
                '}';
    }
}
