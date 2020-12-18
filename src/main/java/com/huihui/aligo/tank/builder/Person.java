package com.huihui.aligo.tank.builder;

import lombok.Getter;
import lombok.Setter;

/**
 * Person是一个复杂对象
 *
 * @author minghui.y
 * @create 2020-12-18 5:32 下午
 **/
@Setter
@Getter
public class Person {
    private String name;
    private int age;
    private String sex;
    private double weight;
    private double height;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
