package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.utils.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * 简单样式的坦克
 *
 * @author minghui.y
 * @create 2020-12-12 10:17 下午
 **/
public class SimpleTank extends BaseTank {


    public SimpleTank( int x, int y, Dir dir, Group group, GameModel gameModel) {
        super( x, y, dir, group, gameModel );
        WIDTH = getGoodTankL().getWidth();
        HEIGHT = getGoodTankL().getHeight();
    }

    @Override
    protected BufferedImage getGoodTankL() {
        return ResourceManager.goodSimpleTankL;
    }

    @Override
    protected BufferedImage getGoodTankR() {
        return ResourceManager.goodSimpleTankR;
    }

    @Override
    protected BufferedImage getGoodTankU() {
        return ResourceManager.goodSimpleTankU;
    }

    @Override
    protected BufferedImage getGoodTankD() {
        return ResourceManager.goodSimpleTankD;
    }

    @Override
    protected BufferedImage getBadTankL() {
        return ResourceManager.badSimpleTankL;
    }

    @Override
    protected BufferedImage getBadTankR() {
        return ResourceManager.badSimpleTankR;
    }

    @Override
    protected BufferedImage getBadTankU() {
        return ResourceManager.badSimpleTankU;
    }

    @Override
    protected BufferedImage getBadTankD() {
        return ResourceManager.badSimpleTankD;
    }
}
