package com.huihui.aligo.tank.abstractFactory;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.model.*;
import com.huihui.aligo.tank.observer.FrameFireObserver;
import com.huihui.aligo.tank.observer.WallFireObserver;

/**
 * 简单样式的工厂
 * 设计模式：
 * 单例模式
 * 抽象工厂
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
    public BaseTank createTank( int x, int y, Dir dir, Group group, GameModel gameModel ) {
        BaseTank tank = new SimpleTank( x, y, dir, group, gameModel );
        //注册开火监听器
        tank.registerFireListener( new FrameFireObserver() );
        tank.registerFireListener( new WallFireObserver() );
        return tank;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
        return new SimpleBullet( x, y, dir, group, gameModel);
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameModel gameModel) {
        return new SimpleExplode( x, y, gameModel );
    }
}
