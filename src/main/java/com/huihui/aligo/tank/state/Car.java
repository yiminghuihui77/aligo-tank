package com.huihui.aligo.tank.state;

/**
 * 汽车类
 *
 * @author minghui.y
 * @create 2020-12-19 5:37 下午
 **/
public class Car {

    public enum CarState {
        OPEN,
        CLOSED,
        RUNNING,
        STOPPED;
    }

    private State state;

    public Car(State state) {
        this.state = state;
    }


    public void openDoor() {
        state.openDoor();
    }

    public void closeDoor() {
        state.closeDoor();
    }

    public void run() {
        state.run();
    }

    public void stop() {
        state.stop();
    }



}
