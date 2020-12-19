package com.huihui.aligo.tank.prototype;

import lombok.Getter;
import lombok.Setter;

/**
 * 地址
 *
 * @author minghui.y
 * @create 2020-12-19 11:58 上午
 **/
@Getter
@Setter
public class Location implements Cloneable {

    private String address;
    private Integer stamp;

    public Location(String address, Integer stamp) {
        this.address = address;
        this.stamp = stamp;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", stamp=" + stamp +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
