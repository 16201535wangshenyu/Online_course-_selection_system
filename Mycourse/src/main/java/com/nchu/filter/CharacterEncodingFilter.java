package com.nchu.filter;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.Charset;

public class CharacterEncodingFilter  implements Filter {
    private String encoding ;
    private  String contentType;
    static{
        System.out.println("Filter 加载了！");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding") ;
        if( encoding == null || encoding.trim().length() == 0  ){
            // 重新设置编码
            encoding = "UTF-8" ;
        }
        if( !Charset.isSupported( encoding )){
            // 重新设置编码
            encoding = "UTF-8" ;
        }
        this.encoding = encoding ;
        this.contentType=filterConfig.getInitParameter("contentType") ;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            request.setCharacterEncoding( this.encoding );
            response.setCharacterEncoding( this.encoding );
            response.setContentType(contentType);
            // 向后传递
            chain.doFilter( request , response );
    }

    @Override
    public void destroy() {

    }
}
