package com.huihui.aligo.tank.model;

import java.awt.*;

/**
 * 简单样式的墙
 *
 * @author minghui.y
 * @create 2020-12-14 4:48 下午
 **/
public class SimpleWall extends BaseWall {



    public SimpleWall( int x, int y, int width, int height ) {
        super(x, y, width, height);
    }

    @Override
    public void paint( Graphics graphics ) {
        graphics.setColor( Color.GRAY );
        graphics.fillRect( x, y, width, height );
    }
}
