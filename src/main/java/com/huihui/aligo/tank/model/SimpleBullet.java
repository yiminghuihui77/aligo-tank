package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.utils.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * 简单样式的子弹
 *
 * @author minghui.y
 * @create 2020-12-13 12:51 下午
 **/
public class SimpleBullet extends BaseBullet {


    public SimpleBullet( int x, int y, Dir dir, Group group, GameModel gameModel ) {
        super( x, y, dir, group, gameModel );
    }

    @Override
    protected BufferedImage getBullet4L() {
        return ResourceManager.bulletL;
    }

    @Override
    protected BufferedImage getBullet4R() {
        return ResourceManager.bulletR;
    }

    @Override
    protected BufferedImage getBullet4U() {
        return ResourceManager.bulletU;
    }

    @Override
    protected BufferedImage getBullet4D() {
        return ResourceManager.bulletD;
    }
}
