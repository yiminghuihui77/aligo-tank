package com.huihui.aligo.tank.chain.servlet;

/**
 * html过滤器
 *
 * @author minghui.y
 * @create 2020-12-18 4:24 下午
 **/
public class HtmlFilter implements Filter {

    @Override
    public void doFilter( ExtRequest request, ExtResponse response, FilterChain filterChain ) {
        //对request进行处理
        request.setRequestData( "<html>"  + request.getRequestData() + "</html>");

        //传递到下个filter（放行）
        filterChain.doFilter( request, response );

        //对response进行处理
        response.setResponseData( response.getResponseData() + "<HtmlFilter的response>" );
    }
}
