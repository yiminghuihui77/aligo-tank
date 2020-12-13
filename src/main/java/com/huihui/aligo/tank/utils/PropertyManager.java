package com.huihui.aligo.tank.utils;

import java.util.Properties;

/**
 * 配置文件管理器
 * 单例模式
 * @author minghui.y
 * @create 2020-12-12 3:32 下午
 **/
public class PropertyManager {

    private PropertyManager() {}

    static Properties properties = new Properties();

    static {
        try {
            properties.load( PropertyManager.class.getClassLoader().getResourceAsStream( "config/init.properties" ));
        } catch (Exception e) {

        }
    }

    /**
     * 获取配置属性
     * @param key
     * @return
     */
    public static String getString(String key) {
        return properties.getProperty( key );
    }

    public static int getInt(String key) {
        return Integer.parseInt( properties.getProperty( key ) );
    }
}
