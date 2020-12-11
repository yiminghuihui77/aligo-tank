package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.utils.ResourceManager;
import com.huihui.aligo.tank.frame.TankFrame;

import java.awt.*;

/**
 * 爆炸model
 *
 * @author minghui.y
 * @create 2020-12-11 6:24 下午
 **/
public class Explode {
    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();

    private int x;
    private int y;

    private int step;

    private TankFrame tankFrame;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        //爆炸声音
//        Audio a = new Audio("audio/explode.wav");
//        a.loop();
    }

    /**
     * 渲染炸弹
     * @param graphics
     */
    public void paint( Graphics graphics) {
        graphics.drawImage( ResourceManager.explodes[step++], x, y, null);
        if (step >= ResourceManager.explodes.length) {
            //销毁炸弹
            tankFrame.getExplodes().remove( this );
        }
    }

}
