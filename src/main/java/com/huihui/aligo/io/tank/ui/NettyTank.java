package com.huihui.aligo.io.tank.ui;

import com.huihui.aligo.io.tank.message.TankStateMessage;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.utils.PropertyManager;
import com.huihui.aligo.tank.utils.ResourceManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @author minghui.y
 * @create 2020-12-21 5:24 下午
 **/
@Getter
@Setter
public class NettyTank {

    private UUID uuid;
    private int x;
    private int y;
    /**
     * 坦克在移动之前的位置
     * 用于坦克&坦克之间碰撞检测策略
     */
    private int oldX;
    private int oldY;

    /**
     * 坦克分组
     */
    private Group group;
    /**
     * 坦克长宽
     */
    public static int WIDTH;
    public static int HEIGHT;
    /**
     * 方向
     */
    private Dir dir;
    private Dir[] availableDirs = new Dir[]{Dir.UP, Dir.DOWN, Dir.LEFT, Dir.RIGHT};
    /**
     * 随机
     */
    private Random random = new Random();

    /**
     * 坦克的存活状态
     */
    private boolean living = true;
    /**
     * 移动状态
     */
    private boolean moving = false;
    /**
     * 移动速度
     */
    private final static int SPEED;
    static {
        SPEED = PropertyManager.getInt( "tankSpeed" );
    }


    public NettyTank(int x, int y , Dir dir, Group group, UUID uuid) {
        this.dir = dir;
        this.group = group;
        this.x = x;
        this.y = y;
        this.uuid = uuid;
    }

    public NettyTank( TankStateMessage message ) {
        this.dir = message.getDir();
        this.group = message.getGroup();
        this.x = message.getX();
        this.y = message.getY();
        this.uuid = message.getUuid();
    }

    public void paint( Graphics graphics) {
        //画出坦克
        paintTank( graphics );

        //移动坦克
        move();

    }

    /**
     * 根据当前状态（方向），画出坦克（图片）
     * @param graphics
     */
    private void paintTank(Graphics graphics) {
        if (!living) {
            //只渲染存活状态的坦克
            return;
        }
        //根据坦克方向获取坦克图片资源
        switch (dir) {
            case LEFT:
                graphics.drawImage( ResourceManager.goodTankL, x, y, null );
                break;
            case RIGHT:
                graphics.drawImage( ResourceManager.goodTankR, x, y, null );
                break;
            case UP:
                graphics.drawImage( ResourceManager.goodTankU, x, y, null );
                break;
            case DOWN:
                graphics.drawImage( ResourceManager.goodTankD, x, y , null );
            default:
                break;
        }
        //画出坦克的uuid
        Color color = graphics.getColor();
        graphics.setColor( Color.ORANGE );
        graphics.drawString( uuid.toString(), x, y - 10 );
        graphics.setColor( color );
    }

    public void move() {
        //停止状态，没必要判断方向且移动
        if (!moving) {
            return;
        }

        //记录坦克移动之前的位置，用于坦克间碰撞的冲突检测
        oldX = x;
        oldY = y;
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
        if (this.x > NettyTankFrame.GAME_WIDTH - WIDTH) {
            x = NettyTankFrame.GAME_WIDTH - WIDTH;
        }
        if (y < 30) {
            y = 30;
        }
        if (y > NettyTankFrame.GAME_WIDTH - HEIGHT) {
            y = NettyTankFrame.GAME_HEIGHT - HEIGHT;
        }
    }

    /**
     * 销毁坦克
     */
    public void die() {
        this.living = false;
    }


}
