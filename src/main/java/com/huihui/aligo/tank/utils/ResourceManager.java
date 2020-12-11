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
     * 坦克&子弹的图片资源
     */
    public static BufferedImage tankL, tankR, tankU, tankD, bulletL, bulletR, bulletU, bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            //加载坦克图片
            tankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankU.gif" ) );
            tankL = ImageUtil.rotateImage( tankU, -90 );
            tankR = ImageUtil.rotateImage( tankU, 90 );
            tankD = ImageUtil.rotateImage( tankU, 180 );

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
