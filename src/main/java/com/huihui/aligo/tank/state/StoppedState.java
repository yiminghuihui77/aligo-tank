package com.huihui.aligo.tank.state;

/**
 * stopped的state
 *
 * @author minghui.y
 * @create 2020-12-19 5:55 下午
 **/
public class StoppedState implements State {
    @Override
    public void openDoor() {
        System.out.println("STOPPED状态下，开门成功");
    }

    @Override
    public void closeDoor() {
        throw new RuntimeException("不能在STOPPED状态下关门");
    }

    @Override
    public void run() {
        System.out.println("STOPPED状态下，开车成功");
    }

    @Override
    public void stop() {
        throw new RuntimeException("不能在STOPPED状态下停车");
    }
}
