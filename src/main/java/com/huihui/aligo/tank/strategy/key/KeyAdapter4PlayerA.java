package com.huihui.aligo.tank.strategy.key;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.model.GameModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 玩家A键盘监听
 *
 * @author minghui.y
 * @create 2020-12-12 2:48 下午
 **/
public class KeyAdapter4PlayerA  extends KeyAdapter {

    /**
     * 窗口
     */
    private GameModel gameModel;

    public KeyAdapter4PlayerA(GameModel gameModel) {
        this.gameModel= gameModel;
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
            case KeyEvent.VK_UP:
                gameModel.getTankA().setDir( Dir.UP );
                break;
            case KeyEvent.VK_DOWN:
                gameModel.getTankA().setDir( Dir.DOWN );
                break;
            case KeyEvent.VK_LEFT:
                gameModel.getTankA().setDir( Dir.LEFT );
                break;
            case KeyEvent.VK_RIGHT:
                gameModel.getTankA().setDir( Dir.RIGHT );
                break;
            case KeyEvent.VK_ENTER:
                //按下回车，移动状态取反
                gameModel.getTankA().setMoving( !gameModel.getTankA().isMoving() );
                break;
            case KeyEvent.VK_SPACE:
                //按下空格键，坦克发射子弹
                gameModel.getTankA().fire();
                break;
            default:
                break;
        }

        //通知系统重新paint（按键影响窗口刷新频率）
//        tankFrame.repaint();
    }
}
