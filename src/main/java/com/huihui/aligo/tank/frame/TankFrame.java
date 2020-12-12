package com.huihui.aligo.tank.frame;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.model.Bullet;
import com.huihui.aligo.tank.model.Explode;
import com.huihui.aligo.tank.model.Tank;
import com.huihui.aligo.tank.strategy.fire.MultiFireStrategy;
import com.huihui.aligo.tank.strategy.key.KeyAdapter4PlayerA;
import com.huihui.aligo.tank.strategy.key.KeyAdapter4PlayerB;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author minghui.y
 * @create 2020-12-10 9:57 下午
 **/
@Getter
@Setter
public class TankFrame extends Frame {

    //主坦克
    private Tank tankA = new Tank( 100, 100 , Dir.RIGHT, Group.GOOD, this);
    private Tank tankB = new Tank( 600, 100 , Dir.RIGHT, Group.GOOD, this);

    //敌方坦克
    private List<Tank> tanks = new ArrayList<>();
    //子弹
    private List<Bullet> bullets = new ArrayList<>();
    //爆炸
    private List<Explode> explodes = new ArrayList<>();

    //避免闪烁
    Image offScreenImage = null;

    /**
     * 定义窗口大小
     */
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    public static final int LOCATION_X = 300;
    public static final int LOCATION_Y = 100;


    public TankFrame() {
        this.setResizable( false );
        this.setTitle( "Tank @yiminghuihui77" );
        this.setSize( GAME_WIDTH, GAME_HEIGHT );
        this.setVisible( true );
        this.setLocation( LOCATION_X, LOCATION_Y );

        //监听窗口事件
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                System.exit( 0 );
            }
        } );
        //监听键盘事件
        addKeyListener(new KeyAdapter4PlayerA( this ) );
        addKeyListener(new KeyAdapter4PlayerB( this ) );
    }


    /**
     * paint方法在窗口被移动修改时，被执行
     */
    @Override
    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor( Color.GREEN );
        graphics.drawString( "子弹的数量：" + bullets.size() , 10, 60 );
        graphics.drawString( "敌方坦克的数量：" + tanks.size() , 120, 60 );
        graphics.drawString( "炸弹数量" + explodes.size() , 10, 80 );
        graphics.setColor( color );

        //渲染主坦克
        tankA.paint( graphics );
        tankB.paint( graphics );

        //渲染多个敌方坦克
        for (int i = 0;i < tanks.size();i++) {
            tanks.get( i ).paint( graphics );
        }

        //渲染多个子弹
//        bullets.forEach( o -> {o.paint( graphics );} );
        //注意：Bullet.move()方法中有对bullets集合做删除动作，不能使用forEach或者迭代器方式遍历集合
        //否则抛出ConcurrentModificationException异常
        for (int i = 0;i < bullets.size();i++) {
            bullets.get(i). paint( graphics );
        }

        //炸弹渲染
        for (int i = 0;i < explodes.size();i++) {
            explodes.get( i ).paint( graphics );
        }

        //界面背景
        graphics.setColor( Color.BLACK );

    }


    /**
     * 更新画面，避免闪烁
     * 在内存中先画好图片，在一次性写到屏幕
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        //获取图片的画笔
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    /**
     * 当子弹移出界面范围，销毁子弹，避免内存移出
     * @param bullet
     */
    public void removeBullet(Bullet bullet) {
        bullets.remove( bullet );
    }


}
