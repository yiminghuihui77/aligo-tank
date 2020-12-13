package com.huihui.aligo.tank.abstractFactory;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.model.*;

/**
 * 设计模式-抽象工厂
 * 产品族：坦克、子弹、爆炸
 * 不同的具体工厂，生产不同样式的产品族
 *
 * 状态：待完成...
 * @author minghui.y
 * @create 2020-12-12 10:10 下午
 **/
public abstract class AbstractGameFactory {

    public abstract BaseTank createTank( int x, int y , Dir dir, Group group, TankFrame tankFrame);

    public abstract BaseBullet createBullet();

    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);

}
