package com.huihui.aligo.tank.state;

/**
 * close状态的State
 *
 * @author minghui.y
 * @create 2020-12-19 5:47 下午
 **/
public class CloseState implements State {
    @Override
    public void openDoor() {
        System.out.println("CLOSE状态下，开门成功");
    }

    @Override
    public void closeDoor() {
        throw new RuntimeException("不能在CLOSE状态关门");
    }

    @Override
    public void run() {
        System.out.println("CLOSE状态下，开车成功");
    }

    @Override
    public void stop() {
        System.out.println("CLOSE状态下，停车成功");
    }
}
