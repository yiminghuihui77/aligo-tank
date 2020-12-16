package com.huihui.aligo.tank.observer;

import com.huihui.aligo.tank.model.BaseTank;

/**
 * 开火事件
 *
 * @author minghui.y
 * @create 2020-12-15 4:29 下午
 **/
public class FireEvent extends BaseEvent<BaseTank> {

    public FireEvent( BaseTank source, String name ) {
        super( source, name );
    }
}
