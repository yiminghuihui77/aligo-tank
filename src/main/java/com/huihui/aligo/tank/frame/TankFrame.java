package com.huihui.aligo.tank.frame;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.model.Bullet;
import com.huihui.aligo.tank.model.Tank;
import lombok.Data;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author minghui.y
 * @create 2020-12-10 9:57 下午
 **/
@Data
public class TankFrame extends Frame {

    private Tank tankA = new Tank( 100, 100 , Dir.RIGHT, this);
//    private Tank tankB = new Tank( 400, 100 , Dir.RIGHT, this);

    private List<Bullet> bullets = new ArrayList<>();

    //避免闪烁
    Image offScreenImage = null;

    /**
     * 定义窗口边界
     */
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    public TankFrame() {
        this.setResizable( false );
        this.setTitle( "Tank" );
        this.setSize( GAME_WIDTH, GAME_HEIGHT );
        this.setVisible( true );

        //监听窗口事件
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                System.exit( 0 );
            }
        } );
        //监听键盘事件
        addKeyListener(new TankKeyAdapter());
    }


    /**
     * paint方法在窗口被移动修改时，被执行
     */
    @Override
    public void paint(Graphics graphics) {
        graphics.drawString( "子弹的数量：" + bullets.size() , 10, 60 );
        tankA.paint( graphics );
//        tankB.paint( graphics );

        //渲染多个子弹
//        bullets.forEach( o -> {o.paint( graphics );} );
        //注意：Bullet.move()方法中有对bullets集合做删除动作，不能使用forEach或者迭代器方式遍历集合
        //否则抛出ConcurrentModificationException异常
        for (int i = 0;i < bullets.size();i++) {
            bullets.get(i). paint( graphics );
        }
    }


    /**
     * 更新画面，避免闪烁
     * 在内存中先画好图片，在一次性写到屏幕
     * @param g
     */
//    @Override
//    public void update(Graphics g) {
//        if (offScreenImage == null) {
//            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
//        }
//        //获取图片的画笔
//        Graphics gOffScreen = offScreenImage.getGraphics();
//        Color c = gOffScreen.getColor();
//        gOffScreen.setColor(Color.BLACK);
//        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
//        gOffScreen.setColor(c);
//        paint(gOffScreen);
//        g.drawImage(offScreenImage, 0, 0, null);
//    }


    /**
     * 键盘监控适配器
     */
    private class TankKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed( KeyEvent e ) {
            //获取键信息
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    tankA.setDir( Dir.UP );
                    break;
                case KeyEvent.VK_DOWN:
                    tankA.setDir( Dir.DOWN );
                    break;
                case KeyEvent.VK_LEFT:
                    tankA.setDir( Dir.LEFT );
                    break;
                case KeyEvent.VK_RIGHT:
                    tankA.setDir( Dir.RIGHT );
                    break;
                case KeyEvent.VK_SPACE:
                    //按下空格，移动状态取反
                    tankA.setMoving( !tankA.isMoving() );
                    break;
                case KeyEvent.VK_CONTROL:
                    //按下ctrl键，坦克发射子弹
                    tankA.fire();
                    break;
                default:
                    break;
            }

            //通知系统重新paint
            repaint();
        }
    }


    /**
     * 当子弹移出界面范围，销毁子弹，避免内存移出
     * @param bullet
     */
    public void removeBullet(Bullet bullet) {
        bullets.remove( bullet );
    }


}
