package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.strategy.fire.FireStrategy;
import com.huihui.aligo.tank.utils.PropertyManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 坦克的抽象父类
 *
 * @author minghui.y
 * @create 2020-12-12 11:41 下午
 **/
@Setter
@Getter
public abstract class BaseTank {

    /**
     * 坦克分组
     */
    private Group group;
    /**
     * 平面坐标
     */
    private int x;
    private int y;
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
     * 坦克持有窗口
     */
    private TankFrame tankFrame;
    /**
     * 移动状态
     */
    private boolean moving = true;
    /**
     * 移动速度
     */
    private final static int SPEED;
    static {
        SPEED = PropertyManager.getInt( "tankSpeed" );
    }
    /**
     * 坦克默认开火策略：单方向开火
     */
    private FireStrategy fireStrategy;


    public BaseTank( int x, int y , Dir dir, Group group, TankFrame tankFrame) {
        this.dir = dir;
        this.group = group;
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        //开火策略
        String fireStrategyName;
        if (Group.GOOD.equals( group )) {
            //从配置文件中获取开火策略的全称类名
            fireStrategyName = PropertyManager.getString( "goodTankFireStrategy" );
        } else {
            fireStrategyName = PropertyManager.getString( "badTankFireStrategy" );
        }
        //反射获取开火策略
        try {
            fireStrategy = (FireStrategy) Class.forName( fireStrategyName ).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


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
                graphics.drawImage( Group.GOOD.equals( group ) ?getGoodTankL() : getBadTankL(), x, y, null );
                break;
            case RIGHT:
                graphics.drawImage( Group.GOOD.equals( group ) ? getGoodTankR() : getBadTankR(), x, y, null );
                break;
            case UP:
                graphics.drawImage( Group.GOOD.equals( group ) ? getGoodTankU() : getBadTankU(), x, y, null );
                break;
            case DOWN:
                graphics.drawImage( Group.GOOD.equals( group ) ? getGoodTankD() : getBadTankD(), x, y , null );
            default:
                break;
        }
    }

    /**
     * 获取不同分组的不同方向的坦克图片资源
     * 交给子类实现
     * @return
     */
    protected abstract BufferedImage getGoodTankL();
    protected abstract BufferedImage getGoodTankR();
    protected abstract BufferedImage getGoodTankU();
    protected abstract BufferedImage getGoodTankD();
    protected abstract BufferedImage getBadTankL();
    protected abstract BufferedImage getBadTankR();
    protected abstract BufferedImage getBadTankU();
    protected abstract BufferedImage getBadTankD();


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
        fireStrategy.fire( this );
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
