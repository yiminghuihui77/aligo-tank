package com.huihui.aligo.io.tank.ui;

import com.huihui.aligo.tank.utils.ResourceManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.UUID;

/**
 * netty版爆炸
 *
 * @author minghui.y
 * @create 2020-12-22 6:17 下午
 **/
@Setter
@Getter
public class NettyExplode {

    private int x;
    private int y;
    private UUID uuid;

    public NettyExplode( int x, int y, UUID uuid ) {
        this.x = x;
        this.y = y;
        this.uuid = uuid;
    }

    public static int WIDTH;
    public static int HEIGHT;
    static {
        WIDTH = ResourceManager.explodes[0].getWidth();
        HEIGHT = ResourceManager.explodes[0].getHeight();
    }

    /**
     * 炸弹帧数
     */
    private int step;


    /**
     * 渲染炸弹
     * @param graphics
     */
    public void paint( Graphics graphics) {
        //渲染炸弹
        graphics.drawImage( ResourceManager.explodes[step++], x, y, null);
        //炸弹帧数达到最好一帧，则销毁炸弹
        if (step >= ResourceManager.explodes.length) {
            //销毁炸弹
            NettyTankFrame.getInstance().removeExplode( this );
        }
    }

}
