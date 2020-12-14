package com.huihui.aligo.tank.chain;

import com.huihui.aligo.tank.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 碰撞检测抽象基类
 *
 * @author minghui.y
 * @create 2020-12-14 3:45 下午
 **/
@Getter
@Setter
public abstract class CollisionHandler {

    private String name;

    private CollisionHandler next;

    public CollisionHandler( String name) {
        this.name = name;
    }

    /**
     * 返回next处理器，方便流式编程
     * @param next
     * @return
     */
    public CollisionHandler setNext(CollisionHandler next) {
        this.next = next;
        return next;
    }

    /**
     * 交给子类实现
     * @param m1
     * @param m2
     * @return
     */
    protected abstract boolean doCollision(BaseModel m1, BaseModel m2);

    /**
     * 检测碰撞
     * 模板方法：定义了一个检测流程，具体检测逻辑由子类实现
     * @param m1
     * @param m2
     */
    public final void collision( BaseModel m1, BaseModel m2 ) {
        if (doCollision( m1, m2 )) {
            //当前节点处理
            done( m1, m2 );
        } else if (this.next != null) {
            //交给下个节点处理
            next.collision( m1, m2 );
        } else {
            //无法检测
            fail( m1, m2 );
        }
    }

    protected void done(BaseModel m1, BaseModel m2) {
        System.out.println(name + "：完成碰撞检测");
    }

    protected void fail(BaseModel m1, BaseModel m2) {
        System.out.println("整个责任链无法检测碰撞");
    }

}
