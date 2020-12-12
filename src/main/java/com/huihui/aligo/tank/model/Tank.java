package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.utils.ResourceManager;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
     * 坦克分组
     */
    private Group group;
    /**
     * 方向
     */
    private Dir dir;
    private Dir[] availableDirs = new Dir[]{Dir.UP, Dir.DOWN, Dir.LEFT, Dir.RIGHT};
    /**
     * 平面坐标
     */
    private int x;
    private int y;
    /**
     * 坦克的长宽(从图片获取)
     */
    public static final int WIDTH = ResourceManager.badTankL.getWidth();
    public static final int HEIGHT = ResourceManager.badTankL.getHeight();
    /**
     * 步进
     */
    private final int SPEED = 10;
    /**
     * 移动状态
     */
    private boolean moving = true;
    /**
     * 坦克的存活状态
     */
    private boolean living = true;
    /**
     * 随机
     */
    private Random random = new Random();
    /**
     * 坦克持有窗口
     */
    private TankFrame tankFrame;


    public Tank( int x, int y , Dir dir, Group group,  TankFrame tankFrame) {
        this.dir = dir;
        this.group = group;
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

        //敌方坦克随机开火&方向
        if (Group.BAD.equals( this.group )) {
            //随机
            if (random.nextInt(10) > 8) {
                fire();
                changeDir();
            }

        }

    }

    /**
     * 随机改变坦克方向
     */
    private void changeDir() {
        this.dir = this.availableDirs[random.nextInt(4)];
    }

    /**
     * 坦克发射子弹
     * 每发射一次，新建一个子弹
     */
    public void fire() {
        //每次发射，创建一个子弹
        //计算子弹发射的坐标点
        int bx = this.x + (WIDTH / 2) - (Bullet.WIDTH / 2);
        int by = this.y + (HEIGHT / 2) - (Bullet.HEIGHT / 2);
        //子弹方向与坦克的方向保持一致；坦克打出的子弹不会误伤自己和友军
        tankFrame.getBullets().add( new Bullet( bx, by, this.dir, this.group, this.tankFrame) );
    }


    /**
     * 根据当前状态（方向），画出坦克（图片）
     * @param graphics
     */
    private void paintTank(Graphics graphics) {
        if (!living) {
            tankFrame.getTanks().remove( this );
            //只渲染存活状态的坦克
            return;
        }

        //根据坦克分组&方向获取坦克图片资源
        switch (dir) {
            case LEFT:
                graphics.drawImage( Group.GOOD.equals( group ) ? ResourceManager.goodTankL : ResourceManager.badTankL, x, y, null );
                break;
            case RIGHT:
                graphics.drawImage( Group.GOOD.equals( group ) ? ResourceManager.goodTankR :ResourceManager.badTankR, x, y, null );
                break;
            case UP:
                graphics.drawImage( Group.GOOD.equals( group ) ? ResourceManager.goodTankU :ResourceManager.badTankU, x, y, null );
                break;
            case DOWN:
                graphics.drawImage( Group.GOOD.equals( group ) ? ResourceManager.goodTankD :ResourceManager.badTankD, x, y , null );
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
        //限制坦克的坐标，必须在界面范围内
        checkBound();
    }

    /**
     * 边界检测
     */
    private void checkBound() {
        if (this.x < 0) {
            x = 0;
        }
        if (this.x > TankFrame.GAME_WIDTH - WIDTH) {
            x = TankFrame.GAME_WIDTH - WIDTH;
        }
        if (y < 30) {
            y = 30;
        }
        if (y > TankFrame.GAME_WIDTH - HEIGHT) {
            y = TankFrame.GAME_HEIGHT - HEIGHT;
        }
    }


    /**
     * 销毁坦克
     */
    public void die() {
        this.living = false;
    }

}
