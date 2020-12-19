package com.huihui.aligo.tank.strategy.fire;

import com.huihui.aligo.tank.model.BaseTank;

import java.io.Serializable;

/**
 * 开火策略
 *
 * @author minghui.y
 * @create 2020-12-12 5:18 下午
 **/
public interface FireStrategy extends Serializable {

    /**
     * 开火策略
     * @param tank
     */
    void fire( BaseTank tank );
}
