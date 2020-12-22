package com.huihui.aligo.io.tank.constant;

/**
 * 数据传输的消息类型
 *
 * @author minghui.y
 * @create 2020-12-22 10:28 上午
 **/
public enum MessageType {
    TANK_JOIN("坦克加入"), TANK_MOVING("坦克移动"), TANK_DIE("坦克销毁");

    private String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
