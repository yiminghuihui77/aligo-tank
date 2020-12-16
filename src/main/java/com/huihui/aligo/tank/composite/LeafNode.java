package com.huihui.aligo.tank.composite;

/**
 * 叶子节点
 *
 * @author minghui.y
 * @create 2020-12-16 4:05 下午
 **/
public class LeafNode<T> extends Node {

    /**
     * 叶子节点用于存储数据
     */
    private T value;

    public LeafNode(String name, T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 打印单个叶子节点
     */
    @Override
    public void print() {
        System.out.println(toString());
    }





    @Override
    public String toString() {
        return "LeafNode{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
