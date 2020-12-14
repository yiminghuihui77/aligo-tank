package com.huihui.aligo.tank.chain;

import com.huihui.aligo.tank.model.BaseBullet;
import com.huihui.aligo.tank.model.BaseModel;
import com.huihui.aligo.tank.model.BaseWall;

import java.awt.*;

/**
 * 子弹&墙碰撞检测handler
 * 处理策略：销毁子弹
 * @author minghui.y
 * @create 2020-12-14 5:41 下午
 **/
public class BulletWallCollisionHandler extends CollisionHandler {

    public BulletWallCollisionHandler( String name ) {
        super( name );
    }

    @Override
    protected boolean doCollision( BaseModel m1, BaseModel m2 ) {
        if (m1 instanceof BaseBullet && m2 instanceof BaseWall) {
            Rectangle rectangle4M1 = new Rectangle(m1.getX(), m1.getY(), BaseBullet.WIDTH, BaseBullet.HEIGHT);
            Rectangle rectangle4M2 = new Rectangle(m2.getX(), m2.getY(), ((BaseWall) m2).getWidth(), ((BaseWall) m2).getHeight());
            if (rectangle4M1.intersects( rectangle4M2 )) {
                //销毁子弹
                ((BaseBullet) m1).die();
            }
            return true;
        } else if (m2 instanceof BaseBullet && m1 instanceof BaseWall) {
            return doCollision( m2, m1 );
        } else {
            return false;
        }
    }
}
