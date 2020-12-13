package com.huihui.aligo.tank.abstractFactory;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.model.*;

/**
 * 简单样式的工厂
 * 设计成单例
 * @author minghui.y
 * @create 2020-12-12 10:15 下午
 **/
public class SimpleGameFactory extends AbstractGameFactory {

    private static final  SimpleGameFactory INSTANCE = new SimpleGameFactory();

    private SimpleGameFactory() {

    }

    public static SimpleGameFactory getInstance() {
        return INSTANCE;
    }



    @Override
    public BaseTank createTank( int x, int y, Dir dir, Group group, TankFrame tankFrame ) {
        return new SimpleTank( x, y, dir, group, tankFrame );
    }

    @Override
    public BaseBullet createBullet() {
        return null;
    }

    @Override
    public BaseExplode createExplode() {
        return null;
    }
}
