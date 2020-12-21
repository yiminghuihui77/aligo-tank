package com.huihui.aligo.io.chat;

import lombok.Getter;
import lombok.Setter;

/**
 * 坦克message
 *
 * @author minghui.y
 * @create 2020-12-21 1:55 下午
 **/
@Getter
@Setter
public class TankMessage {
    private int x;
    private int y;

    public TankMessage( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TankMessage{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
