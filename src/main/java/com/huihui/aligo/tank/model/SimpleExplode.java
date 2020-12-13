package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.utils.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * 简单样式的爆炸
 *
 * @author minghui.y
 * @create 2020-12-13 12:21 下午
 **/
public class SimpleExplode extends BaseExplode {


    public SimpleExplode( int x, int y, GameModel gameModel ) {
        super( x, y, gameModel );
    }

    @Override
    protected BufferedImage[] getExplodes() {
        return ResourceManager.simpleExplodes;
    }
}
