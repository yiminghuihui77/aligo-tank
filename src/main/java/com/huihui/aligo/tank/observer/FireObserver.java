package com.huihui.aligo.tank.observer;

/**
 * 开火监听器的抽象基类
 *
 * @author minghui.y
 * @create 2020-12-16 2:29 下午
 **/
public abstract class FireObserver implements Observer{
    public abstract void handleEvent( FireEvent event );
}
