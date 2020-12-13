package com.huihui.aligo.tank;

import com.huihui.aligo.tank.abstractFactory.SimpleGameFactory;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.utils.PropertyManager;

/**
 * 启动类
 * @author minghui.y
 * @create 2020-12-10 8:26 下午
 **/
public class HelloTank {

    public static void main( String[] args ) throws InterruptedException {
        TankFrame frame = new TankFrame();

        int badTankNumber = PropertyManager.getInt( "badTankNumber" );
        for (int i = 1;i < (badTankNumber + 1);i ++) {
            //创建四个敌方坦克
            frame.getTanks().add( SimpleGameFactory.getInstance().createTank( 100 + i * 100, 100 + i * 100, Dir.RIGHT, Group.BAD, frame  ) );
        }

        while (true) {
            //不断刷新，坦克会根据方向自己移动
            Thread.sleep( 100 );
            frame.repaint();
        }
    }

}
