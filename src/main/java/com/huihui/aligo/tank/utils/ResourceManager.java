package com.huihui.aligo.tank.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * 资源管理器
 * 类加载的时候，将图片资源文件加载好
 * @author minghui.y
 * @create 2020-12-11 2:27 下午
 **/
public class ResourceManager {



    /**
     * multi
     * 坦克的图片资源
     */
    public static BufferedImage badTankL, badTankR, badTankU, badTankD;
    public static BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;

    /**
     * simple
     * 坦克的图片资源
     */
    public static BufferedImage badSimpleTankL, badSimpleTankR, badSimpleTankU, badSimpleTankD;
    public static BufferedImage goodSimpleTankL, goodSimpleTankR, goodSimpleTankU, goodSimpleTankD;

    /**
     * 子弹图片
     * simple
     */
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    /**
     * 子弹图片
     * multi
     */
    public static BufferedImage multiBulletL, multiBulletR, multiBulletU, multiBulletD;

    /**
     * multi 炸弹
     */
    public static BufferedImage[] explodes = new BufferedImage[16];
    public static BufferedImage[] simpleExplodes = new BufferedImage[11];

    static {
        try {
            //从配置文件中加载坦克样式
            String badMultiTankStyle = PropertyManager.getString( "badMultiTankStyle" );
            String goodMultiTankStyle = PropertyManager.getString( "goodMultiTankStyle" );
            String simpleTankStyle = PropertyManager.getString( "simpleTankStyle" );
            //子弹样式
            String simpleBulletStyle = PropertyManager.getString( "simpleBulletStyle" );
            String multiBulletStyle = PropertyManager.getString( "multiBulletStyle" );

            //复杂样式坦克
            //加载敌方坦克图片
            badTankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( badMultiTankStyle ) );
            badTankL = ImageUtil.rotateImage( badTankU, -90 );
            badTankR = ImageUtil.rotateImage( badTankU, 90 );
            badTankD = ImageUtil.rotateImage( badTankU, 180 );

            //加载友军坦克图片
            goodTankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( goodMultiTankStyle) );
            goodTankL = ImageUtil.rotateImage( goodTankU, -90 );
            goodTankR = ImageUtil.rotateImage( goodTankU, 90 );
            goodTankD = ImageUtil.rotateImage( goodTankU, 180 );

            //简单样式的坦克
            badSimpleTankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( simpleTankStyle ) );
            badSimpleTankL = ImageUtil.rotateImage( badSimpleTankU, -90 );
            badSimpleTankR = ImageUtil.rotateImage( badSimpleTankU, 90 );
            badSimpleTankD = ImageUtil.rotateImage( badSimpleTankU, 180 );
            //简单样式的友军坦克&敌方坦克一样
            goodSimpleTankU = badSimpleTankU;
            goodSimpleTankL = badSimpleTankL;
            goodSimpleTankR = badSimpleTankR;
            goodSimpleTankD = badSimpleTankD;


            //加载simple样式的子弹
            bulletU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( simpleBulletStyle ) );
            bulletL = ImageUtil.rotateImage( bulletU, -90 );
            bulletR = ImageUtil.rotateImage( bulletU, 90 );
            bulletD = ImageUtil.rotateImage( bulletU, 180 );
            //加载multi样式的子弹
            multiBulletU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( multiBulletStyle ) );
            multiBulletD = multiBulletU;
            multiBulletL = multiBulletU;
            multiBulletR = multiBulletU;

            //加载multi样式的炸弹
            for(int i = 0;i < 16; i++) {
                explodes[i] = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/e" + (i + 1) + ".gif" ) );
            }
            //加载simple样式的炸弹
            for (int i = 0;i < 11;i ++) {
                simpleExplodes[i] = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/" + i + ".gif" ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
