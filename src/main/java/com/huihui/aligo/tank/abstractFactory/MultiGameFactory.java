package com.huihui.aligo.tank.abstractFactory;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.model.*;

/**
 * 复杂样式的工厂
 * 设计模式：
 * 单例模式
 * 抽象工厂
 * @author minghui.y
 * @create 2020-12-12 10:16 下午
 **/
public class MultiGameFactory extends AbstractGameFactory {

    private static final MultiGameFactory INSTANCE = new MultiGameFactory();

    private MultiGameFactory() {}

    public static MultiGameFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public BaseTank createTank( int x, int y, Dir dir, Group group, TankFrame tankFrame ) {
        return new MultiTank( x, y, dir, group, tankFrame );
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new MultiBullet( x, y, dir, group, tankFrame );
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new MultiExplode( x, y, tankFrame );
    }
}
