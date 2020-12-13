package com.huihui.aligo.tank.abstractFactory;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.model.*;

/**
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
    public BaseBullet createBullet() {
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new MultiExplode( x, y, tankFrame );
    }
}
