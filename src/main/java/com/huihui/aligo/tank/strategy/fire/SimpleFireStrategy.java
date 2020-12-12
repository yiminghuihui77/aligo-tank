package com.huihui.aligo.tank.strategy.fire;

import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.model.Bullet;
import com.huihui.aligo.tank.model.Tank;
import com.huihui.aligo.tank.utils.Audio;

/**
 * 简单的开火策略
 *
 * @author minghui.y
 * @create 2020-12-12 5:24 下午
 **/
public class SimpleFireStrategy implements FireStrategy {


    /**
     * 每次只发射一个子弹
     * @param tank
     */
    @Override
    public void fire( Tank tank ) {
        //每次发射，创建一个子弹
        //计算子弹发射的坐标点
        int bx = tank.getX() + (Tank.WIDTH / 2) - (Bullet.WIDTH / 2);
        int by = tank.getY() + (Tank.HEIGHT / 2) - (Bullet.HEIGHT / 2);
        //子弹方向与坦克的方向保持一致；坦克打出的子弹不会误伤自己和友军
        tank.getTankFrame().getBullets().add( new Bullet( bx, by, tank.getDir(), tank.getGroup(), tank.getTankFrame()) );

        //开火声音
//        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
