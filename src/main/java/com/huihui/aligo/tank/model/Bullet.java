package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.frame.TankFrame;
import lombok.Data;

import java.awt.*;

/**
 * 子弹model
 *
 * @author minghui.y
 * @create 2020-12-11 11:32 上午
 **/
@Data
public class Bullet {
    private int x;
    private int y;
    private static final int SPEED = 20;
    private int width = 20;
    private int height = 20;
    private Dir dir;

    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint( Graphics graphics ) {
        Color color = graphics.getColor();
        //画子弹
        graphics.setColor( Color.RED );
        graphics.fillOval(x, y, width, height);
        graphics.setColor( color );

        //移动子弹
        move();

    }

    /**
     * 子弹移动
     */
    private void move() {
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

        if (x < 0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_WIDTH) {
            tankFrame.removeBullet( this );
        }
    }



}
