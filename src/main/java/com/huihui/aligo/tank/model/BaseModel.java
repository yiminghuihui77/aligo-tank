package com.huihui.aligo.tank.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * 所有model的抽象基类
 * 例如：坦克、子弹、爆炸、墙
 *
 * @author minghui.y
 * @create 2020-12-13 9:02 下午
 **/
@Getter
@Setter
public abstract class BaseModel {
    protected int x;
    protected int y;

    public abstract void paint( Graphics graphics );
}
