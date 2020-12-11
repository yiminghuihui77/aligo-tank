package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.utils.ResourceManager;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * 子弹model
 *
 * @author minghui.y
 * @create 2020-12-11 11:32 上午
 **/
@Getter
@Setter
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
     * 子弹分组
     */
    private Group group;
    /**
     * 子弹存活状态
     */
    private boolean living = true;
    /**
     * 子弹持有窗口引用
     */
    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

        if (!living) {
            tankFrame.getBullets().remove( this );
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

        //子弹超出界面范围，则销毁子弹
        if (x < 0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_WIDTH) {
            tankFrame.removeBullet( this );
        }

        //子弹移动过程中，若与某个坦克碰撞，则销毁子弹和坦克
        for (Tank tank : tankFrame.getTanks()) {
            collideWith( tank );
        }


    }

    /**
     * 判断子弹是否与坦克碰撞
     * @param tank
     */
    public void collideWith(Tank tank) {
        //如果目标坦克是友军，不检测碰撞
        if (this.group.equals( tank.getGroup() )) {
            return;
        }
        //TODO 每次渲染都会new，待优化
        Rectangle bulletRec = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle tankRec = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (bulletRec.intersects( tankRec )) {
            //子弹&坦克发生碰撞
            tank.die();
            this.die();
            //窗口添加炸弹
            tankFrame.getExplodes().add( new Explode( this.x, this.y, tankFrame ) );
        }
    }


    /**
     * 销毁子弹
     */
    public void die() {
        this.living = false;
    }


}
