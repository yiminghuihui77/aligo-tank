package com.huihui.aligo.tank.chain;

import com.huihui.aligo.tank.model.BaseModel;
import com.huihui.aligo.tank.model.BaseTank;

import java.awt.*;

/**
 * 坦克与坦克的碰撞检测handler
 * 处理：当两个坦克发生碰撞，各自回到碰撞之前的位置
 *
 * @author minghui.y
 * @create 2020-12-14 4:12 下午
 **/
public class TankTankCollisionHandler extends CollisionHandler {

    public TankTankCollisionHandler( String name) {
        super( name);
    }

    @Override
    protected boolean doCollision( BaseModel m1, BaseModel m2 ) {
        //坦克与坦克碰撞检测
        if (m1 instanceof BaseTank && m2 instanceof BaseTank) {
            Rectangle rectangle4M1 = new Rectangle(m1.getX(), m1.getY(), BaseTank.WIDTH, BaseTank.HEIGHT);
            Rectangle rectangle4M2 = new Rectangle(m2.getX(), m2.getY(), BaseTank.WIDTH, BaseTank.HEIGHT);
            if (rectangle4M1.intersects( rectangle4M2 )) {
                //若发生碰撞，则两个坦克各自回到移动前的位置
                m1.setX( ((BaseTank) m1).getOldX() );
                m1.setY( ((BaseTank) m1).getOldY() );
                m2.setX( ((BaseTank) m2).getOldX() );
                m2.setY( ((BaseTank) m2).getOldY() );
            }
            return true;
        }
        return false;
    }
}
