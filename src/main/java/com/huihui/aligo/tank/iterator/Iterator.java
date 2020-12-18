package com.huihui.aligo.tank.iterator;

/**
 * 迭代器接口
 *
 * @author minghui.y
 * @create 2020-12-17 5:58 下午
 **/
public interface Iterator<E> {

    /**
     * 是否与下个元素
     * @return
     */
    boolean hasNext();

    /**
     * 获取下个元素
     * @return
     */
    E next();

}
