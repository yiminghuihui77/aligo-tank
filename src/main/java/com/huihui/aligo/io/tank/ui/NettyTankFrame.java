package com.huihui.aligo.io.tank.ui;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * netty版TankFrame
 *
 * @author minghui.y
 * @create 2020-12-21 5:19 下午
 **/
@Getter
@Setter
public class NettyTankFrame extends Frame {

    private Random random = new Random();

    /**
     * 单例话
     */
    private static final NettyTankFrame INSTANCE = new NettyTankFrame();

    public static NettyTankFrame getInstance() {
        return INSTANCE;
    }

    //避免闪烁
    Image offScreenImage = null;

    /**
     * 自己的坦克
     */
    private NettyTank mainTank = new NettyTank( random.nextInt(800), random.nextInt(600), Dir.DOWN, Group.GOOD, UUID.randomUUID() );

    private Map<String, NettyTank> tankMap = new HashMap<>();

    private Map<String, NettyBullet> bulletMap = new HashMap<>();


    /**
     * 定义窗口大小
     */
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    public static final int LOCATION_X = 300;
    public static final int LOCATION_Y = 100;




    private  NettyTankFrame() {
        this.setResizable( false );
        this.setTitle( "Netty-Tank @yiminghuihui77" );
        this.setSize( GAME_WIDTH, GAME_HEIGHT );
        this.setLocation( LOCATION_X, LOCATION_Y );

        //监听窗口事件
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                System.exit( 0 );
            }
        } );
        //监听键盘事件
        addKeyListener(new MainTankKeyAdapter());

        //初始化完成后，界面可视
        this.setVisible( true );
    }



    @Override
    public void paint( Graphics g ) {

        Color color = g.getColor();
        g.setColor( Color.ORANGE );
        g.drawString( "敌方坦克数量：" + tankMap.values().size(), 10, 60 );
        g.drawString( "子弹数量：" + bulletMap.size(), 10, 80 );
        g.setColor( color );

        //渲染自己的坦克
        mainTank.paint( g );

        //渲染其他坦克
        tankMap.values().stream().forEach( o -> o.paint( g ) );

        //渲染子弹
        //TODO CurrentModificationException异常待解决
        Iterator<NettyBullet> iterator = bulletMap.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().paint( g );
        }

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


    public void addBadTank(NettyTank tank) {
        tankMap.put( tank.getUuid().toString(), tank );
    }

    public NettyTank getBadTank(String uuid) {
        return tankMap.get( uuid );
    }


    /**
     * 主战坦克按键监听
     */
    public  class MainTankKeyAdapter extends KeyAdapter {
        /**
         * 按键按下
         * @param e
         */
        @Override
        public void keyPressed( KeyEvent e ) {
            //获取键信息
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    mainTank.changeDir( Dir.UP );
                    break;
                case KeyEvent.VK_DOWN:
                    mainTank.changeDir( Dir.DOWN );
                    break;
                case KeyEvent.VK_LEFT:
                    mainTank.changeDir( Dir.LEFT );
                    break;
                case KeyEvent.VK_RIGHT:
                    mainTank.changeDir( Dir.RIGHT );
                    break;
                case KeyEvent.VK_ENTER:
                    //回车键，改变移动状态
                    mainTank.changeMoving();
                    break;
                case KeyEvent.VK_SPACE:
                    //按下空格键，坦克发射子弹
                    mainTank.fire();
                    break;
                default:
                    break;
            }
        }



    }

    public void removeBullet(NettyBullet bullet) {
        bulletMap.remove( bullet.getUuid().toString() );
    }

    public void addBullet(NettyBullet bullet) {
        bulletMap.put( bullet.getUuid().toString(), bullet );
    }

}
