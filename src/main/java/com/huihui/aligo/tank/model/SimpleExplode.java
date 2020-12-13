package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.utils.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * 简单样式的爆炸
 *
 * @author minghui.y
 * @create 2020-12-13 12:21 下午
 **/
public class SimpleExplode extends BaseExplode {


    public SimpleExplode( int x, int y, TankFrame tankFrame ) {
        super( x, y, tankFrame );
    }

    @Override
    protected BufferedImage[] getExplodes() {
        return ResourceManager.simpleExplodes;
    }
}
