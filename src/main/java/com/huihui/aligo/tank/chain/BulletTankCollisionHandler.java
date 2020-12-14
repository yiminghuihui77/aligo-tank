package com.huihui.aligo.tank.chain;

import com.huihui.aligo.tank.abstractFactory.MultiGameFactory;
import com.huihui.aligo.tank.abstractFactory.SimpleGameFactory;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.model.BaseBullet;
import com.huihui.aligo.tank.model.BaseExplode;
import com.huihui.aligo.tank.model.BaseModel;
import com.huihui.aligo.tank.model.BaseTank;

/**
 * 子弹&坦克碰撞检测handler
 * 处理：当子弹与敌方坦克发生碰撞：
 *      1、销毁子弹和坦克
 *      2、生成爆炸
 * @author minghui.y
 * @create 2020-12-14 3:59 下午
 **/
public class BulletTankCollisionHandler extends CollisionHandler {


    public BulletTankCollisionHandler( String name) {
        super( name);
    }

    @Override
    protected boolean doCollision( BaseModel m1, BaseModel m2 ) {
        //子弹、坦克碰撞检测
        if (m1 instanceof BaseBullet && m2 instanceof BaseTank) {
            BaseBullet bullet = (BaseBullet) m1;
            BaseTank tank = (BaseTank) m2;

            //如果目标坦克是友军，不检测碰撞
            if (((BaseBullet) m1).getGroup().equals( ((BaseTank) m2).getGroup() )) {
                return true;
            }
            //修改子弹矩形坐标
            bullet.getBulletRec().x = bullet.getX();
            bullet.getBulletRec().y = bullet.getY();
            //修改坦克矩形坐标
            bullet.getTankRec().x = tank.getX();
            bullet.getTankRec().y = tank.getY();
            //两个矩形是否重叠
            if (bullet.getBulletRec().intersects( bullet.getTankRec() )) {
                //子弹&坦克发生碰撞
                tank.die();
                bullet.die();

                //添加炸弹
                int ex = tank.getX() + (BaseTank.WIDTH / 2) - (BaseExplode.WIDTH / 2) ;
                int ey = tank.getY() + (BaseTank.HEIGHT / 2) - (BaseExplode.HEIGHT / 2) ;
                //友军打出multi的炸弹；敌军打出simple的炸弹
                bullet.getGameModel().addModel( Group.GOOD.equals( bullet.getGroup() ) ?
                        MultiGameFactory.getInstance().createExplode( ex, ey, bullet.getGameModel() ) :
                        SimpleGameFactory.getInstance().createExplode( ex, ey, bullet.getGameModel() ) );
            }
            return true;
        } else if (m1 instanceof BaseTank && m2 instanceof BaseBullet) {
            return doCollision( m2, m1 );
        } else {
            return false;
        }
    }
}
