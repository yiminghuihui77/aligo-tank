package com.huihui.aligo.tank.observer;

import com.huihui.aligo.tank.model.BaseTank;

/**
 * 墙监听器
 *
 * @author minghui.y
 * @create 2020-12-15 7:15 下午
 **/
public class WallFireObserver extends FireObserver {


    @Override
    public void handleEvent( FireEvent event ) {
        BaseTank tank = event.getSource();

        System.out.println("WallObserver 监听到开火事件");
    }
}
