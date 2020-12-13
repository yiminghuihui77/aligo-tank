package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.utils.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * 复杂样式的坦克
 *
 * @author minghui.y
 * @create 2020-12-13 11:49 上午
 **/
public class MultiTank extends BaseTank {

    public MultiTank( int x, int y, Dir dir, Group group, TankFrame tankFrame ) {
        super( x, y, dir, group, tankFrame );
        WIDTH = getGoodTankL().getWidth();
        HEIGHT = getGoodTankL().getHeight();
    }


    @Override
    protected BufferedImage getGoodTankL() {
        return ResourceManager.goodTankL;
    }

    @Override
    protected BufferedImage getGoodTankR() {
        return ResourceManager.goodTankR;
    }

    @Override
    protected BufferedImage getGoodTankU() {
        return ResourceManager.goodTankU;
    }

    @Override
    protected BufferedImage getGoodTankD() {
        return ResourceManager.goodTankD;
    }

    @Override
    protected BufferedImage getBadTankL() {
        return ResourceManager.badTankL;
    }

    @Override
    protected BufferedImage getBadTankR() {
        return ResourceManager.badTankR;
    }

    @Override
    protected BufferedImage getBadTankU() {
        return ResourceManager.badTankU;
    }

    @Override
    protected BufferedImage getBadTankD() {
        return ResourceManager.badTankD;
    }
}
