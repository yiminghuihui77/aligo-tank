package com.huihui.aligo.tank.proxy.dynamicproxy.jdk;

import com.huihui.aligo.tank.constant.Dir;
import com.huihui.aligo.tank.constant.Group;
import com.huihui.aligo.tank.model.SimpleTank;
import com.huihui.aligo.tank.proxy.Movable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * 核心：InvocationHandler
 * @author minghui.y
 * @create 2020-12-17 10:50 上午
 **/
public class TankInvocationHandler implements InvocationHandler {

    private Movable m;

    public TankInvocationHandler( Movable m) {
        this.m = m;
    }

    @Override
    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
        System.out.println("坦克开始移动!!!");
        Object o = method.invoke( m, args );
        System.out.println("坦克结束移动!!!");
        return o;
    }

    public static void main( String[] args ) {
        //将JDK动态代理生成的代理类以文件形式存下来（默认在内存中）
//        jdk8及之前：
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        jdk8之后：
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        //目标被代理对象
        Movable tank = new SimpleTank( 1, 1, Dir.DOWN, Group.GOOD, null );

        Movable proxy = (Movable) Proxy.newProxyInstance( TankInvocationHandler.class.getClassLoader(),
                new Class[]{Movable.class}, new TankInvocationHandler( tank));

        proxy.move();
    }
}
