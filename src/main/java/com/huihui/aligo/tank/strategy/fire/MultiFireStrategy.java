package com.huihui.aligo.tank.strategy.fire;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.model.Bullet;
import com.huihui.aligo.tank.model.Tank;

/**
 * 四个方向同时开火策略
 *
 * @author minghui.y
 * @create 2020-12-12 5:28 下午
 **/
public class MultiFireStrategy implements FireStrategy {


    @Override
    public void fire( Tank tank ) {
        //每次发射，创建一个子弹
        //计算子弹发射的坐标点
        int bx = tank.getX() + (Tank.WIDTH / 2) - (Bullet.WIDTH / 2);
        int by = tank.getY() + (Tank.HEIGHT / 2) - (Bullet.HEIGHT / 2);
        //子弹方向与坦克的方向保持一致；坦克打出的子弹不会误伤自己和友军
        tank.getTankFrame().getBullets().add( new Bullet( bx, by, Dir.UP, tank.getGroup(), tank.getTankFrame()) );
        tank.getTankFrame().getBullets().add( new Bullet( bx, by, Dir.DOWN, tank.getGroup(), tank.getTankFrame()) );
        tank.getTankFrame().getBullets().add( new Bullet( bx, by, Dir.LEFT, tank.getGroup(), tank.getTankFrame()) );
        tank.getTankFrame().getBullets().add( new Bullet( bx, by, Dir.RIGHT, tank.getGroup(), tank.getTankFrame()) );

    }
}
