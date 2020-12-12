package com.huihui.aligo.tank.strategy.key;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.frame.TankFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 玩家B键盘监听
 *
 * @author minghui.y
 * @create 2020-12-12 2:48 下午
 **/
public class KeyAdapter4PlayerB extends KeyAdapter {

    /**
     * 窗口
     */
    private TankFrame tankFrame;

    public KeyAdapter4PlayerB( TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    /**
     * 监听键盘按下事件
     * @param e
     */
    @Override
    public void keyPressed( KeyEvent e ) {
        //获取键信息
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                tankFrame.getTankB().setDir( Dir.UP );
                break;
            case KeyEvent.VK_S:
                tankFrame.getTankB().setDir( Dir.DOWN );
                break;
            case KeyEvent.VK_A:
                tankFrame.getTankB().setDir( Dir.LEFT );
                break;
            case KeyEvent.VK_D:
                tankFrame.getTankB().setDir( Dir.RIGHT );
                break;
            case KeyEvent.VK_Z:
                //按下Z键，移动状态取反
                tankFrame.getTankB().setMoving( !tankFrame.getTankB().isMoving() );
                break;
            case KeyEvent.VK_F:
                //按下空格键，坦克发射子弹
                tankFrame.getTankB().fire();
                break;
            default:
                break;
        }

        //通知系统重新paint（按键影响窗口刷新频率）
//        tankFrame.repaint();
    }
}
