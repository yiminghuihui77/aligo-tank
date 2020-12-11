package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * 坦克model
 *
 * @author minghui.y
 * @create 2020-12-11 10:49 上午
 **/
@Getter
@Setter
public class Tank {
    private Dir dir;
    private int x;
    private int y;
    private final int SPEED = 10;

    public Tank( Dir dir, int x, int y ) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    /**
     * 坦克自己控制方向和移动
     * @param graphics
     */
    public void paint( Graphics graphics) {
        graphics.fillRect( x, y, 50, 50 );
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
    }
}
