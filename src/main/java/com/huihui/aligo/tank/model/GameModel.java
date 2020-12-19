package com.huihui.aligo.tank.model;

import com.huihui.aligo.tank.abstractFactory.MultiGameFactory;
import com.huihui.aligo.tank.abstractFactory.SimpleGameFactory;
import com.huihui.aligo.tank.chain.*;
import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.utils.PropertyManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;
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
public class GameModel implements Serializable {

    /**
     * 所有的model由GameModel维护
     */
    private List<BaseModel> allModels = new ArrayList<>();
    //主坦克
    private BaseTank tankA;
    private BaseTank tankB;

    /**
     * 记录敌军坦克、爆炸、子弹的数量，用于展示
     */
    private int badTankNumber;
    private int explodeNumber;
    private int bulletNumber;

    /**
     * 以下完成单例模式
     */
    private static final GameModel INSTANCE = new GameModel();

    public static GameModel getInstance() {
        return INSTANCE;
    }

    private GameModel() {
        //友军坦克
        tankA = MultiGameFactory.getInstance().createTank( 100, 100 , Dir.RIGHT, Group.GOOD, this );
        tankB = MultiGameFactory.getInstance().createTank( 600, 100 , Dir.RIGHT, Group.GOOD, this);
        allModels.add( tankA );
        allModels.add( tankB );

        //敌军坦克
        int badTankNumber = PropertyManager.getInt( "badTankNumber" );
        for (int i = 1;i < (badTankNumber + 1);i ++) {
            //创建四个敌方坦克
            addModel( SimpleGameFactory.getInstance().createTank( 100 + i * 100, 100 + i * 100, Dir.RIGHT, Group.BAD, this  ) );
        }

        //创建四个实体墙（这里突出了抽象工厂不适合扩展产品的缺点）
        allModels.add( new SimpleWall( 50, 150, 100, 50) );
        allModels.add( new SimpleWall( 500, 150, 100, 50) );
        allModels.add( new SimpleWall( 50, 500, 100, 50) );
        allModels.add( new SimpleWall( 500, 500, 100, 50) );


    }




    public void paint( Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor( Color.GREEN );
        graphics.drawString( "子弹的数量：" + bulletNumber , 10, 60 );
        graphics.drawString( "敌方坦克的数量：" + badTankNumber , 120, 60 );
        graphics.drawString( "炸弹数量" + explodeNumber , 10, 80 );
        graphics.setColor( color );

        //渲染所有model
        for (int i = 0;i < allModels.size();i++) {
            allModels.get( i ).paint( graphics );
        }

        //所有model之间进行碰撞检测
        //调停者模式：各类model之间的碰撞检测，关系负载，统一通过GameModel协调
        //新建各种碰撞检测处理器
        CollisionHandler handler1 = new BulletTankCollisionHandler( "子弹&坦克碰撞处理器" );
        CollisionHandler handler2 = new TankTankCollisionHandler( "坦克&坦克碰撞处理器" );
        CollisionHandler handler3 = new TankWallCollisionHandler( "坦克&墙碰撞处理器" );
        CollisionHandler handler4 = new BulletWallCollisionHandler( "子弹&墙碰撞处理器" );
        handler1.setNext( handler2 ).setNext( handler3 ).setNext( handler4 );

        for (int i = 0;i < allModels.size() - 1; i++) {
            for (int j = i + 1;j < allModels.size();j++) {
                //交个责任链去处理model之间的碰撞检测
                handler1.collision( allModels.get( i ), allModels.get( j ) );
            }
        }

        //界面背景
        graphics.setColor( Color.BLACK );
    }


    /**
     * 向GameModel添加model
     * @param model
     */
    public void addModel(BaseModel model) {
        if (model instanceof BaseBullet) {
            bulletNumber++;
        }
        if (model instanceof BaseTank) {
            if (Group.BAD.equals( ((BaseTank) model).getGroup() )) {
                badTankNumber++;
            }
        }
        if (model instanceof BaseExplode) {
            explodeNumber++;
        }
        allModels.add( model );
    }

    /**
     * 从GameModel移出model
     * @param model
     */
    public void removeModel(BaseModel model) {
        if (model instanceof BaseBullet) {
            bulletNumber--;
        }
        if (model instanceof BaseTank) {
            if (Group.BAD.equals( ((BaseTank) model).getGroup() )) {
                badTankNumber--;
            }
        }
        if (model instanceof BaseExplode) {
            explodeNumber--;
        }
        allModels.remove( model );
    }


}
