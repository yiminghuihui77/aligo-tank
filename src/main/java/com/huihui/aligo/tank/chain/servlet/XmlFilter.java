package com.huihui.aligo.tank.chain.servlet;

/**
 * Xml过滤器
 *
 * @author minghui.y
 * @create 2020-12-18 4:31 下午
 **/
public class XmlFilter implements Filter {


    @Override
    public void doFilter( ExtRequest request, ExtResponse response, FilterChain filterChain ) {
        //对request进行处理
        request.setRequestData( "<xml>"  + request.getRequestData() + "</xml>");

        //传递到下个filter（放行）
        filterChain.doFilter( request, response );

        //对response进行处理
        response.setResponseData(response.getResponseData() + "<XmlFilter的response>" );
    }
}
