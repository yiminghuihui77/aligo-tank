package com.huihui.aligo.tank.constant;

/**
 * 坦克分组
 *
 * @author minghui.y
 * @create 2020-12-11 4:33 下午
 **/
public enum  Group {

    GOOD("友方"), BAD("敌方");

    private String name;

    Group(String name) {
        this.name = name;
    }
}
