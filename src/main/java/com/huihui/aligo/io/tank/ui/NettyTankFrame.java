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
import java.util.List;

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

//    private Map<String, NettyTank> tankMap = new HashMap<>();
    private List<NettyTank> tanks = new ArrayList<>();

//    private Map<String, NettyBullet> bulletMap = new HashMap<>();
    private List<NettyBullet> bullets = new ArrayList<>();

//    private Map<String, NettyExplode> explodeMap = new HashMap<>();
    private List<NettyExplode> explodes = new ArrayList<>();


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

        //渲染自己的坦克
        mainTank.paint( g );

        //渲染其他坦克
        for (int i = 0;i < tanks.size();i++) {
            tanks.get( i ).paint( g );
        }

        //渲染子弹
        for (int i = 0;i < bullets.size();i++) {
            bullets.get( i ).paint( g );
        }

        //渲染炸弹
        for (int i = 0;i < explodes.size();i ++) {
            explodes.get( i ).paint( g );
        }

        Color color = g.getColor();
        g.setColor( Color.ORANGE );
        g.drawString( "敌方坦克数量：" + tanks.size(), 10, 60 );
        g.drawString( "子弹数量：" + bullets.size(), 10, 80 );
        //标识主战坦克
        g.drawString( "主战坦克", mainTank.getX(), mainTank.getY() - 20 );
        g.setColor( color );

        if (!mainTank.isLiving()) {
            g.setColor( Color.RED );
            g.drawString( "你的坦克已被击毁", 300, 300 );
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
        bullets.remove( bullet );
    }

    public void addBullet(NettyBullet bullet) {
        bullets.add( bullet );
    }

    public void addTank(NettyTank tank) {
        tanks.add( tank );
    }

    public void removeTank(NettyTank tank) {
        tanks.remove( tank );
    }

    public void addExplode(NettyExplode explode) {
        explodes.add( explode );
    }

    public void removeExplode(NettyExplode explode) {
        explodes.remove( explode );
    }

    public NettyTank getBadTank(String uuid) {
        Optional<NettyTank> optional = tanks.stream().filter( o -> o.getUuid().toString().equals( uuid ) ).findFirst();

        return optional.orElse( null );
    }

    public NettyBullet getBullet(String uuid) {
        Optional<NettyBullet> optional = bullets.stream().filter( o -> o.getUuid().toString().equals( uuid ) ).findFirst();
        return optional.orElse( null );
    }


}
