package com.huihui.aligo.tank.iterator;

/**
 * 自定义集合接口
 * 集合接口继承可迭代接口，表示集合都是可迭代的
 * @author minghui.y
 * @create 2020-12-17 6:00 下午
 **/
public interface MyCollection<E> extends Iteratable {

    void add(E e);

    E get(int index);
}
