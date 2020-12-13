package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.abstractFactory.MultiGameFactory;
import com.huihui.aligo.tank.abstractFactory.SimpleGameFactory;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.utils.PropertyManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 维护所有的model，以及外界与model的交互
 * 设计模式：门面模式 Facade、单例模式
 * @author minghui.y
 * @create 2020-12-13 5:39 下午
 **/
@Setter
@Getter
public class GameModel {

    /**
     * 以下完成单例模式
     */
    private static final GameModel INSTANCE = new GameModel();

    public static GameModel getInstance() {
        return INSTANCE;
    }

    private GameModel() {
        int badTankNumber = PropertyManager.getInt( "badTankNumber" );
        for (int i = 1;i < (badTankNumber + 1);i ++) {
            //创建四个敌方坦克
            tanks.add( SimpleGameFactory.getInstance().createTank( 100 + i * 100, 100 + i * 100, Dir.RIGHT, Group.BAD, this  ) );
        }
    }

    /**
     * 所有的model由GameModel维护
     */
    //主坦克
    private BaseTank tankA = MultiGameFactory.getInstance().createTank( 100, 100 , Dir.RIGHT, Group.GOOD, this );
    private BaseTank tankB = MultiGameFactory.getInstance().createTank( 600, 100 , Dir.RIGHT, Group.GOOD, this);

    //敌方坦克
    private List<BaseTank> tanks = new ArrayList<>();
    //子弹
    private List<BaseBullet> bullets = new ArrayList<>();
    //爆炸
    private List<BaseExplode> explodes = new ArrayList<>();


    public void paint( Graphics graphics) {
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
     * 当子弹移出界面范围，销毁子弹，避免内存移出
     * @param bullet
     */
    public void removeBullet(BaseBullet bullet) {
        bullets.remove( bullet );
    }


}
