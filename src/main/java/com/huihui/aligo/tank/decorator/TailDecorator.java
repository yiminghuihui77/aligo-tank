package com.huihui.aligo.tank.decorator;

import com.huihui.aligo.tank.model.BaseModel;

import java.awt.*;

/**
 * 尾部装饰器
 *
 * @author minghui.y
 * @create 2020-12-15 12:36 下午
 **/
public class TailDecorator extends BaseDecorator {



    public TailDecorator( BaseModel model ) {
        super( model );
    }

    @Override
    public void paint( Graphics graphics ) {
        this.x = model.getX();
        this.y = model.getY();

       model.paint( graphics );

       Color color = graphics.getColor();
       graphics.setColor( Color.WHITE );
       graphics.drawLine( model.getX(), model.getY(), x, y );
       graphics.setColor( color );

    }
}
