package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.utils.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * 复杂样式的炸弹
 *
 * @author minghui.y
 * @create 2020-12-13 12:24 下午
 **/
public class MultiExplode extends BaseExplode {


    public MultiExplode( int x, int y, GameModel gameModel) {
        super( x, y, gameModel );
    }

    @Override
    protected BufferedImage[] getExplodes() {
        return ResourceManager.explodes;
    }
}
