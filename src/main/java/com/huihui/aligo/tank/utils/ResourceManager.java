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
     * 坦克的图片资源
     */
    public static BufferedImage badTankL, badTankR, badTankU, badTankD;
    public static BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;

    /**
     * 子弹图片
     */
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            //加载敌方坦克图片
            badTankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/BadTank1.png" ) );
            badTankL = ImageUtil.rotateImage( badTankU, -90 );
            badTankR = ImageUtil.rotateImage( badTankU, 90 );
            badTankD = ImageUtil.rotateImage( badTankU, 180 );

            //加载友军坦克图片
            goodTankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/GoodTank1.png" ) );
            goodTankL = ImageUtil.rotateImage( goodTankU, -90 );
            goodTankR = ImageUtil.rotateImage( goodTankU, 90 );
            goodTankD = ImageUtil.rotateImage( goodTankU, 180 );



            //加载子弹图片
            bulletU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/bulletU.gif" ) );
            bulletL = ImageUtil.rotateImage( bulletU, -90 );
            bulletR = ImageUtil.rotateImage( bulletU, 90 );
            bulletD = ImageUtil.rotateImage( bulletU, 180 );

            //爆炸效果💥
            for(int i = 0;i < 16; i++) {
                explodes[i] = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/e" + (i + 1) + ".gif" ) );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
