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

    public static BufferedImage tankL, tankR, tankU, tankD;

    static {
        try {
            tankL = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankL.gif" ) );
            tankR = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankR.gif" ) );
            tankU = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankU.gif" ) );
            tankD = ImageIO.read( ResourceManager.class.getClassLoader().getResourceAsStream( "images/tankD.gif" ) );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
