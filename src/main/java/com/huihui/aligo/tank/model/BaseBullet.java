package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.frame.TankFrame;
import com.huihui.aligo.tank.proxy.Movable;
import com.huihui.aligo.tank.utils.PropertyManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 子弹的抽象父类
 *
 * @author minghui.y
 * @create 2020-12-12 11:44 下午
 **/
@Getter
@Setter
public abstract class BaseBullet extends BaseModel implements Movable {

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
    private GameModel gameModel;


    public BaseBullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        this.bulletRec = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        this.tankRec = new Rectangle(0, 0, BaseTank.WIDTH, BaseTank.HEIGHT);
        WIDTH = getBullet4U().getWidth();
        HEIGHT = getBullet4U().getHeight();
    }


    /**
     * 渲染子弹
     * @param graphics
     */
    @Override
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
            gameModel.removeModel( this );
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
    @Override
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
        if (x < 0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_WIDTH) {
            gameModel.removeModel( this );
        }
    }





    /**
     * 销毁子弹
     */
    public void die() {
        this.living = false;
    }


}
