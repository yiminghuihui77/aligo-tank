package com.huihui.aligo.tank.command;

/**
 * Command的抽象基类
 *
 * @author minghui.y
 * @create 2020-12-19 11:11 上午
 **/
public abstract class AbstractCommand {
    /**
     * 执行
     */
    public abstract void doIt();

    /**
     * 回退
     */
    public abstract void undo();
}
