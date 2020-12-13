package com.huihui.aligo.tank.frame;

import com.huihui.aligo.tank.model.GameModel;
import com.huihui.aligo.tank.strategy.key.KeyAdapter4PlayerA;
import com.huihui.aligo.tank.strategy.key.KeyAdapter4PlayerB;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author minghui.y
 * @create 2020-12-10 9:57 下午
 **/
@Getter
@Setter
public class TankFrame extends Frame {

    /**
     * 门面模式，TankFrame通过GameModel与各类model交互
     */
    private GameModel gameModel = GameModel.getInstance();

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
        addKeyListener(new KeyAdapter4PlayerA( gameModel ) );
        addKeyListener(new KeyAdapter4PlayerB( gameModel ) );
    }


    /**
     * paint方法在窗口被移动修改时，被执行
     */
    @Override
    public void paint(Graphics graphics) {
        //通过门面模式交互
        gameModel.paint( graphics );

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





}
