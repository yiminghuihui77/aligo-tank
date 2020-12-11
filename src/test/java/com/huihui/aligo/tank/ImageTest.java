package com.huihui.aligo.tank;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author minghui.y
 * @create 2020-12-11 2:14 ����
 **/
public class ImageTest {


    @Test
    public void testImage() throws Exception {

        //��ȡclasspath�µ���Դ�ļ�
        BufferedImage image = ImageIO.read( ImageTest.class.getClassLoader().getResourceAsStream( "images/BadTank1.png" )  );
        Assert.assertNotNull( image );
    }

}
