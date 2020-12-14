package com.huihui.aligo.tank.decorator;

import com.huihui.aligo.tank.model.BaseModel;

import java.awt.*;

/**
 * 矩形装饰器
 *
 * @author minghui.y
 * @create 2020-12-14 9:49 下午
 **/
public class RectangleDecorator extends BaseDecorator {

    public RectangleDecorator( BaseModel model ) {
        super(model);
    }

    @Override
    public void paint( Graphics graphics ) {
        model.paint( graphics );
        //增加装饰
        Color color = graphics.getColor();
        graphics.setColor( Color.WHITE );
        graphics.drawRect( model.getX(), model.getY(), 20, 20 );
        graphics.setColor( color );
    }
}
