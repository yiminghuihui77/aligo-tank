package com.huihui.aligo.tank.state;

/**
 * 状态接口
 *
 * @author minghui.y
 * @create 2020-12-19 5:43 下午
 **/
public interface State {

    void openDoor();

    void closeDoor();

    void run();

    void stop();
}
