package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.ResourceManager;
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
    /**
     * 子弹坐标
     */
    private int x;
    private int y;
    /**
     * 子弹步进
     */
    private static final int SPEED = 20;
    /**
     * 子弹长宽
     */
    public static final int WIDTH = ResourceManager.bulletL.getWidth();
    public static final int HEIGHT = ResourceManager.bulletL.getHeight();
    /**
     * 子弹方向
     */
    private Dir dir;
    /**
     * 子弹持有窗口引用
     */
    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint( Graphics graphics ) {
       //画图形子弹
        paintBullet( graphics );

        //移动子弹
        move();

    }

    /**
     * 画子弹
     * @param graphics
     */
    public void paintBullet(Graphics graphics) {
//        Color color = graphics.getColor();
        //画子弹
//        graphics.setColor( Color.RED );
//        graphics.fillOval(x, y, WIDTH, HEIGHT);
//        graphics.setColor( color );

        switch (dir) {
            case LEFT:
                graphics.drawImage( ResourceManager.bulletL, x, y, null );
                break;
            case RIGHT:
                graphics.drawImage( ResourceManager.bulletR, x, y, null );
                break;
            case UP:
                graphics.drawImage( ResourceManager.bulletU, x, y, null );
                break;
            case DOWN:
                graphics.drawImage( ResourceManager.bulletD, x, y , null );
            default:
                break;
        }

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
