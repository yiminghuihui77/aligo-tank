package com.huihui.aligo.tank.chain;

import com.huihui.aligo.tank.model.BaseModel;
import com.huihui.aligo.tank.model.BaseTank;
import com.huihui.aligo.tank.model.BaseWall;

import java.awt.*;

/**
 * 坦克&墙碰撞检测处理器
 * 处理策略：坦克回到碰撞之前的位置
 * @author minghui.y
 * @create 2020-12-14 5:29 下午
 **/
public class TankWallCollisionHandler extends CollisionHandler {

    public TankWallCollisionHandler( String name ) {
        super( name );
    }

    @Override
    protected boolean doCollision( BaseModel m1, BaseModel m2 ) {
        if (m1 instanceof BaseTank && m2 instanceof BaseWall) {
            Rectangle rectangle4M1 = new Rectangle(m1.getX(), m1.getY(), BaseTank.WIDTH, BaseTank.HEIGHT);
            Rectangle rectangle4M2 = new Rectangle(m2.getX(), m2.getY(), ((BaseWall) m2).getWidth(), ((BaseWall) m2).getHeight());
            if (rectangle4M1.intersects( rectangle4M2 )) {
                m1.setX( ((BaseTank) m1).getOldX() );
                m1.setY( ((BaseTank) m1).getOldY() );
            }

            return true;
        } else if (m2 instanceof BaseTank && m1 instanceof BaseWall) {
            return doCollision( m2, m1 );
        } else {
            return false;
        }
    }
}
