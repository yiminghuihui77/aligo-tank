package com.huihui.aligo.tank.state;

/**
 * open状态的state
 *
 * @author minghui.y
 * @create 2020-12-19 5:44 下午
 **/
public class OpenState implements State {


    @Override
    public void openDoor() {
        throw new RuntimeException("不能在OPEN状态开门");
    }

    @Override
    public void closeDoor() {
        System.out.println("OPEN状态，关门成功");
    }

    @Override
    public void run() {
        throw new RuntimeException("不能在OPEN状态开车");

    }

    @Override
    public void stop() {
        throw new RuntimeException("不能在OPEN状态停车");
    }
}
