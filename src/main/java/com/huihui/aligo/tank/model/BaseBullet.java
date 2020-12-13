package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.abstractFactory.MultiGameFactory;
import com.huihui.aligo.tank.abstractFactory.SimpleGameFactory;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.utils.PropertyManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 子弹的抽象父类
 *
 * @author minghui.y
 * @create 2020-12-12 11:44 下午
 **/
@Getter
@Setter
public abstract class BaseBullet {

    /**
     * 子弹坐标
     */
    private int x;
    private int y;
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


    public BaseBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        this.bulletRec = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        this.tankRec = new Rectangle(0, 0, BaseTank.WIDTH, BaseTank.HEIGHT);
        WIDTH = getBullet4U().getWidth();
        HEIGHT = getBullet4U().getHeight();
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
            tankFrame.getBullets().remove( this );
            //只渲染存活的子弹
            return;
        }

        switch (dir) {
            case LEFT:
                graphics.drawImage( getBullet4L(), x, y, null );
                break;
            case RIGHT:
                graphics.drawImage( getBullet4R(), x, y, null );
                break;
            case UP:
                graphics.drawImage( getBullet4U(), x, y, null );
                break;
            case DOWN:
                graphics.drawImage( getBullet4D(), x, y , null );
            default:
                break;
        }

    }

    /**
     * 获取上下左右四个方向的子弹图片
     * @return
     */
    protected abstract BufferedImage getBullet4L();
    protected abstract BufferedImage getBullet4R();
    protected abstract BufferedImage getBullet4U();
    protected abstract BufferedImage getBullet4D();


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
        List<BaseTank> allTanks = new ArrayList<>();
        allTanks.addAll( tankFrame.getTanks() );
        allTanks.add( tankFrame.getTankA() );
        allTanks.add( tankFrame.getTankB() );
        for (BaseTank tank : allTanks) {
            collideWith( tank );
        }
    }


    /**
     * 判断子弹是否与坦克碰撞
     * @param tank
     */
    public void collideWith( BaseTank tank) {
        //如果目标坦克是友军，不检测碰撞
        if (this.group.equals( tank.getGroup() )) {
            return;
        }
        //修改子弹矩形坐标
        bulletRec.x = this.x;
        bulletRec.y = this.y;
        //修改坦克矩形坐标
        tankRec.x = tank.getX();
        tankRec.y = tank.getY();
        //两个矩形是否重叠
        if (bulletRec.intersects( tankRec )) {
            //子弹&坦克发生碰撞
            tank.die();
            this.die();

            //添加炸弹
            int ex = tank.getX() + (BaseTank.WIDTH / 2) - (BaseExplode.WIDTH / 2) ;
            int ey = tank.getY() + (BaseTank.HEIGHT / 2) - (BaseExplode.HEIGHT / 2) ;
            //友军打出multi的炸弹；敌军打出simple的炸弹
            tankFrame.getExplodes().add( Group.GOOD.equals( this.group ) ?
                    MultiGameFactory.getInstance().createExplode( ex, ey, tankFrame ) :
                    SimpleGameFactory.getInstance().createExplode( ex, ey, tankFrame ) );
        }
    }


    /**
     * 销毁子弹
     */
    public void die() {
        this.living = false;
    }


}
