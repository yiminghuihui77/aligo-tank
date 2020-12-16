package com.huihui.aligo.tank.observer;

import lombok.Getter;
import lombok.Setter;

/**
 * 事件抽象基类
 *
 * @author minghui.y
 * @create 2020-12-15 4:33 下午
 **/
@Getter
@Setter
public abstract class BaseEvent<T> {

    /**
     * 事件源
     */
    private T source;
    /**
     * 事件名称
     */
    private String name;

    public BaseEvent( T source, String name ) {
        this.source = source;
        this.name = name;
    }
}
