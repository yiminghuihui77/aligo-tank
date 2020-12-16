package com.huihui.aligo.tank.decorator;

import com.huihui.aligo.tank.model.BaseModel;

/**
 * 装饰器的抽象父类
 *
 * @author minghui.y
 * @create 2020-12-14 9:44 下午
 **/
public abstract class BaseDecorator extends BaseModel {

    protected BaseModel model;

    public BaseDecorator(BaseModel model) {
        this.model = model;
    }
}

