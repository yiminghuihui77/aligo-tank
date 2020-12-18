package com.huihui.aligo.tank.chain.servlet;

/**
 * 过滤器接口
 *
 * @author minghui.y
 * @create 2020-12-18 4:00 下午
 **/
public interface Filter {

    void doFilter(ExtRequest request, ExtResponse response, FilterChain filterChain);

}
