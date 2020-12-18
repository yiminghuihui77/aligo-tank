package com.huihui.aligo.tank.chain.servlet;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链
 * 模拟javax.servlet.FilterChain的实现
 * @author minghui.y
 * @create 2020-12-18 4:03 下午
 **/
@Setter
@Getter
public class FilterChain {


    /**
     * 用于存储Filter
     */
    private List<Filter> filters = new ArrayList<>();
    /**
     * 用于存储下个Filter的索引
     */
    private int index = 0;

    public void addFilter(Filter filter) {
        filters.add( filter );
    }

    /**
     * 将request和response传递到责任链中
     * @param request
     * @param response
     */
    public void doFilter(ExtRequest request, ExtResponse response) {
        if (index >= filters.size()) {
            return;
        }
        //取出index位置处的Filter进行处理
        Filter filter = filters.get( index );
        index++;

        filter.doFilter( request, response, this );
    }

    public static void main( String[] args ) {
        FilterChain chain = new FilterChain();
        chain.addFilter( new HtmlFilter() );
        chain.addFilter( new XmlFilter() );

        ExtRequest request = new ExtRequest();
        request.setRequestData( "请求数据" );
        ExtResponse response = new ExtResponse();

        chain.doFilter( request, response );

        System.out.println(request.getRequestData());
        System.out.println(response.getResponseData());

    }

}
