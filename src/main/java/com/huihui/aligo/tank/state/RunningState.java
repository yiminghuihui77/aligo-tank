package com.huihui.aligo.tank.state;

/**
 * running状态的state
 *
 * @author minghui.y
 * @create 2020-12-19 5:52 下午
 **/
public class RunningState implements State {
    @Override
    public void openDoor() {
        throw new RuntimeException("不能在RUNNING状态下开门");
    }

    @Override
    public void closeDoor() {
        throw new RuntimeException("不能在RUNNING状态下关门");
    }

    @Override
    public void run() {
        throw new RuntimeException("不能在RUNNING状态下开车");
    }

    @Override
    public void stop() {
        System.out.println("RUNNING状态下，停车成功");
    }
}
