package com.huihui.aligo.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * 资源管理器
 * 类加载的时候，将图片资源文件加载好
 * @author minghui.y
 * @create 2020-12-11 2:27 下午
 **/
public class ResourceManager {

    public static BufferedImage tankL, tankR, tankU, tankD, bulletL, bulletR, bulletU, bulletD;

    static {
        try {
            //加载坦克图片
            tankL = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankL.gif" ) );
            tankR = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankR.gif" ) );
            tankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankU.gif" ) );
            tankD = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankD.gif" ) );

            //加载子弹图片
            bulletL = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/bulletL.gif" ) );
            bulletR = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/bulletR.gif" ) );
            bulletU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/bulletU.gif" ) );
            bulletD = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/bulletD.gif" ) );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
