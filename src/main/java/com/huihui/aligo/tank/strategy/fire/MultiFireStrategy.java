package com.huihui.aligo.tank.strategy.fire;

import com.huihui.aligo.tank.abstractFactory.MultiGameFactory;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.model.BaseBullet;
import com.huihui.aligo.tank.model.BaseTank;

/**
 * 四个方向同时开火策略
 *
 * @author minghui.y
 * @create 2020-12-12 5:28 下午
 **/
public class MultiFireStrategy implements FireStrategy {


    @Override
    public void fire( BaseTank tank ) {
        //每次发射，创建一个子弹
        //计算子弹发射的坐标点
        int bx = tank.getX() + (BaseTank.WIDTH / 2) - (BaseBullet.WIDTH / 2);
        int by = tank.getY() + (BaseTank.HEIGHT / 2) - (BaseBullet.HEIGHT / 2);
        //子弹方向与坦克的方向保持一致；坦克打出的子弹不会误伤自己和友军
        tank.getGameModel().addModel( MultiGameFactory.getInstance().createBullet(  bx, by, Dir.UP, tank.getGroup(), tank.getGameModel() ));
        tank.getGameModel().addModel( MultiGameFactory.getInstance().createBullet(  bx, by, Dir.DOWN, tank.getGroup(), tank.getGameModel() ) );
        tank.getGameModel().addModel( MultiGameFactory.getInstance().createBullet(  bx, by, Dir.LEFT, tank.getGroup(), tank.getGameModel() ));
        tank.getGameModel().addModel( MultiGameFactory.getInstance().createBullet(  bx, by, Dir.RIGHT, tank.getGroup(), tank.getGameModel() ) );

    }
}
