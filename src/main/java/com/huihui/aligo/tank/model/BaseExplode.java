package com.huihui.aligo.tank.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 爆炸的抽象父类
 *
 * @author minghui.y
 * @create 2020-12-12 11:43 下午
 **/
@Getter
@Setter
public abstract class BaseExplode {

    public static int WIDTH;
    public static int HEIGHT;

    /**
     * 炸弹帧数
     */
    private int step;
    /**
     * 炸弹坐标
     */
    private int x;
    private int y;
    /**
     * 门面模式
     */
    private GameModel gameModel;


    public BaseExplode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
        WIDTH = getExplodes()[0].getWidth();
        HEIGHT = getExplodes()[0].getHeight();
    }


    /**
     * 渲染炸弹
     * @param graphics
     */
    public void paint( Graphics graphics) {
        //渲染炸弹
        graphics.drawImage( getExplodes()[step++], x, y, null);
       //炸弹帧数达到最好一帧，则销毁炸弹
        if (step >= getExplodes().length) {
            //销毁炸弹
            gameModel.getExplodes().remove( this );
        }
    }

    /**
     * 获取炸弹样式，由自子类实现
     * 设计模式：模板方法
     * @return
     */
    protected abstract BufferedImage[] getExplodes();
}
