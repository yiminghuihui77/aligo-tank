package com.huihui.aligo.tank.iterator;

/**
 * 这个接口表示容器是可迭代的
 *
 * @author minghui.y
 * @create 2020-12-17 5:57 下午
 **/
public interface Iteratable<T> {

    Iterator<T> iterator();
}
