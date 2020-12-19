package com.huihui.aligo.tank;

import com.huihui.aligo.tank.frame.TankFrame;

/**
 * 启动类
 * @author minghui.y
 * @create 2020-12-10 8:26 下午
 **/
public class HelloTank {

    public static void main( String[] args ) throws InterruptedException {
        TankFrame frame = new TankFrame();

        while (true) {
            //不断刷新，坦克会根据方向自己移动
            Thread.sleep( 200 );
            frame.repaint();
        }
    }

}
