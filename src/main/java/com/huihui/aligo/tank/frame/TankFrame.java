package com.huihui.aligo.tank.frame;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.model.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author minghui.y
 * @create 2020-12-10 9:57 下午
 **/
public class TankFrame extends Frame {

    private Tank tank = new Tank( Dir.RIGHT, 100, 100 );

    public TankFrame() {
        this.setResizable( false );
        this.setTitle( "Tank" );
        this.setSize( 800, 600 );
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
        tank.paint( graphics );
    }


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
                    tank.setDir( Dir.UP );
                    break;
                case KeyEvent.VK_DOWN:
                    tank.setDir( Dir.DOWN );
                    break;
                case KeyEvent.VK_LEFT:
                    tank.setDir( Dir.LEFT );
                    break;
                case KeyEvent.VK_RIGHT:
                    tank.setDir( Dir.RIGHT );
                    break;
            }

            //通知系统重新paint
            repaint();
        }
    }


}
