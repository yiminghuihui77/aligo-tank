package com.huihui.aligo.io.tank.ui;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.model.BaseTank;
import com.huihui.aligo.tank.utils.PropertyManager;
import com.huihui.aligo.tank.utils.ResourceManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.UUID;

/**
 * netty版子弹
 *
 * @author minghui.y
 * @create 2020-12-22 4:36 下午
 **/
@Getter
@Setter
public class NettyBullet {

    private int x;
    private int y;

    private UUID uuid;

    /**
     * 子弹&坦克所在矩形（用于碰撞检测）
     */
    private Rectangle bulletRec;
    private Rectangle tankRec;
    /**
     * 子弹步进
     */
    private static final int SPEED;
    static {
        SPEED = PropertyManager.getInt( "bulletSpeed" );
    }
    /**
     * 子弹长宽
     */
    public static int WIDTH;
    public static int HEIGHT;
    static {
        WIDTH = ResourceManager.bulletU.getWidth();
        HEIGHT = ResourceManager.bulletU.getHeight();
    }
    /**
     * 子弹方向
     */
    private Dir dir;
    /**
     * 子弹分组
     */
    private Group group;
    /**
     * 子弹存活状态
     */
    private boolean living = true;


    public NettyBullet(int x, int y, Dir dir, Group group, UUID uuid) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.uuid = uuid;
        this.bulletRec = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        this.tankRec = new Rectangle(0, 0, BaseTank.WIDTH, BaseTank.HEIGHT);
    }



    /**
     * 渲染子弹
     * @param graphics
     */
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

        if (!living) {
            NettyTankFrame.getInstance().removeBullet( this );
            //只渲染存活的子弹
            return;
        }

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
    public void move() {
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

        //子弹超出界面范围，则销毁子弹
        if (x < 0 || y <0 || x > NettyTankFrame.GAME_WIDTH || y > NettyTankFrame.GAME_WIDTH) {
            NettyTankFrame.getInstance().removeBullet( this );
        }
    }





    /**
     * 销毁子弹
     */
    public void die() {
        this.living = false;
    }

}
