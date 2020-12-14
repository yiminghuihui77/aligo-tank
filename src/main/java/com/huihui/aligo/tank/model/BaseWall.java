package com.huihui.aligo.tank.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 墙的抽象基类
 *
 * @author minghui.y
 * @create 2020-12-14 4:45 下午
 **/
@Getter
@Setter
public abstract class BaseWall extends BaseModel {

    /**
     * 墙的宽和高
     */
    protected int width;
    protected int height;

    public BaseWall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
