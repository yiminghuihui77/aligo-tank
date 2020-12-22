package com.huihui.aligo.io.tank.constant;

/**
 * 数据传输的消息类型
 *
 * @author minghui.y
 * @create 2020-12-22 10:28 上午
 **/
public enum MessageType {
    TANK_JOIN("坦克加入"),
    TANK_DIR_CHANGE("坦克方向改变"),
    TANK_MOVING_CHANGE("坦克移动"),
    TANK_DIE("坦克销毁"),
    BULLET_JOIN("子弹加入"),
    //以下不用，因为子弹会在各个客户端进行碰撞检测，生成爆炸和销毁
    BULLET_DIE("子弹销毁"),
    EXPLODE_JOIN("爆炸加入"),
    EXPLODE_DIE("爆炸销毁");

    private String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
