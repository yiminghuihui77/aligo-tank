package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.utils.ResourceManager;

import java.awt.image.BufferedImage;

/**
 * 复杂样式的子弹
 *
 * @author minghui.y
 * @create 2020-12-13 12:53 下午
 **/
public class MultiBullet extends BaseBullet {

    public MultiBullet( int x, int y, Dir dir, Group group, TankFrame tankFrame ) {
        super( x, y, dir, group, tankFrame );
    }

    @Override
    protected BufferedImage getBullet4L() {
        return ResourceManager.multiBulletL;
    }

    @Override
    protected BufferedImage getBullet4R() {
        return ResourceManager.multiBulletR;
    }

    @Override
    protected BufferedImage getBullet4U() {
        return ResourceManager.multiBulletU;
    }

    @Override
    protected BufferedImage getBullet4D() {
        return ResourceManager.multiBulletD;
    }
}
