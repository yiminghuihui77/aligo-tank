package com.huihui.aligo.tank;

import com.huihui.aligo.tank.frame.TankFrame;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Frame初识：窗口类
 * @author minghui.y
 * @create 2020-12-10 8:26 下午
 **/
public class HelloFrame {

    public static void main( String[] args ) throws InterruptedException {
        Frame f = new TankFrame();

        while (true) {
            TimeUnit.MILLISECONDS.sleep( 500 );
            f.repaint();
        }
    }

}
