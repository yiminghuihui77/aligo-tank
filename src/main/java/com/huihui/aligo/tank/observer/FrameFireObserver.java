package com.huihui.aligo.tank.observer;

import com.huihui.aligo.tank.model.BaseTank;

/**
 * 窗口观察者（监听者）
 *
 * @author minghui.y
 * @create 2020-12-15 4:35 下午
 **/
public class FrameFireObserver extends FireObserver {

    @Override
    public void handleEvent( FireEvent event ) {
        BaseTank tank = event.getSource();

        System.out.println("FrameObserver 监听到开火事件");
    }
}
