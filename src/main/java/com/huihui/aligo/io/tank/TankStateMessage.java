package com.huihui.aligo.io.tank;

import com.huihui.aligo.io.tank.ui.NettyTank;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.UUID;

/**
 * 表示坦克状态的数据传输
 * @author minghui.y
 * @create 2020-12-21 3:40 下午
 **/
@Getter
@Setter
public class TankStateMessage {

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

    /**
     * 将对象转为byte[]
     * @return
     */
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
