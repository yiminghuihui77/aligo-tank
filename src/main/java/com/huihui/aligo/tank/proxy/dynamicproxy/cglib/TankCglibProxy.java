package com.huihui.aligo.tank.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CBLIB方式生成代理对象
 * 核心：MethodInterceptor
 *
 * @author minghui.y
 * @create 2020-12-17 3:00 下午
 **/
public class TankCglibProxy {


    public static void main( String[] args ) {
        //以下设置没作用
//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass( Tank.class );
        //设置拦截器
        enhancer.setCallback( new TankMethodInterceptor() );

        Tank tank = (Tank) enhancer.create();
        tank.move();
        tank.fire();


    }



    public static class TankMethodInterceptor implements MethodInterceptor {

        /**
         * cblib生成代理对象的核心接口方法
         * @param o
         * @param method
         * @param objects
         * @param methodProxy
         * @return
         * @throws Throwable
         */
        @Override
        public Object intercept( Object o, Method method, Object[] objects, MethodProxy methodProxy ) throws Throwable {

            //获取方法名称，只对特定方法进行织入增强
            String methodName = methodProxy.getSignature().getName();
            if ("move".equals( methodName )) {
                System.out.println("cblib前置方法...");
            }

            //调用父类的方法
            Object result = methodProxy.invokeSuper( o, objects );

            if ("move".equals( methodName )) {
                System.out.println("cblib后置方法...");
            }

            return result;
        }
    }


    public static class Tank {

        public void move() {
            System.out.println("我是一个小坦克，我在移动...");
        }

        public void fire() {
            System.out.println("我是一个小坦克，我在开火...");
        }

    }

}
