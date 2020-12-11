package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.ResourceManager;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.frame.TankFrame;
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
    /**
     * 方向
     */
    private Dir dir;
    /**
     * 平面坐标
     */
    private int x;
    private int y;

    private int width = 50;
    private int height = 50;
    /**
     * 步进
     */
    private final int SPEED = 10;
    /**
     * 移动状态
     */
    private boolean moving = false;
    /**
     * 坦克持有窗口
     */
    private TankFrame tankFrame;


    public Tank( int x, int y , Dir dir, TankFrame tankFrame) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    /**
     * 坦克自己控制方向和移动
     * @param graphics
     */
    public void paint( Graphics graphics) {
        //画出坦克
        paintTank( graphics );

        //移动坦克
        move();

    }

    public void fire() {
        //每次发射，创建一个子弹
        tankFrame.getBullets().add( new Bullet( this.x, this.y, this.dir , this.tankFrame) );
    }


    /**
     * 根据当前状态（方向），画出坦克（图片）
     * @param graphics
     */
    private void paintTank(Graphics graphics) {
        //graphics.fillRect( x, y, width, height );

        switch (dir) {
            case LEFT:
                graphics.drawImage( ResourceManager.tankL, x, y, null );
                break;
            case RIGHT:
                graphics.drawImage( ResourceManager.tankR, x, y, null );
                break;
            case UP:
                graphics.drawImage( ResourceManager.tankU, x, y, null );
                break;
            case DOWN:
                graphics.drawImage( ResourceManager.tankD, x, y , null );
            default:
                break;
        }
    }

    /**
     * 坦克移动
     */
    private void move() {
        //停止状态，没必要判断方向且移动
        if (!moving) {
            return;
        }
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
