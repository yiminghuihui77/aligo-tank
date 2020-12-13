package com.huihui.aligo.tank.abstractFactory;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
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
    public BaseTank createTank( int x, int y, Dir dir, Group group, GameModel gameModel ) {
        return new MultiTank( x, y, dir, group, gameModel );
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
        return new MultiBullet( x, y, dir, group, gameModel );
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameModel gameModel) {
        return new MultiExplode( x, y, gameModel );
    }
}
