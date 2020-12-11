package com.huihui.aligo.tank.constant;

/**
 * Dir
 *
 * @author minghui.y
 * @create 2020-12-11 10:08 上午
 **/
public enum Dir {

    UP("上"),DOWN("下"), LEFT("左"),RIGHT("右");

    private String name;

    Dir(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
