package com.huihui.aligo.tank.proxy.staticproxy;

import com.huihui.aligo.tank.proxy.Movable;

/**
 * 坦克的代理
 *
 * @author minghui.y
 * @create 2020-12-17 10:42 上午
 **/
public class TankMovableProxy implements Movable {

    /**
     * 代理对象持有被代理对象的引用
     */
    private Movable movable;

    public TankMovableProxy(Movable movable) {
        this.movable = movable;
    }


    @Override
    public void move() {
        //前置增强
        System.out.println("坦克开始移动...");
        movable.move();
        //后置增强
        System.out.println("坦克结束移动...");
    }
}
