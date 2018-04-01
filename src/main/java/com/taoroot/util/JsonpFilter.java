package com.taoroot.util;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: taoroot
 * @date: 2017/12/4
 * @description:
 */

public class JsonpFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest arg0,
                                    HttpServletResponse arg1, FilterChain arg2)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        // 指定允许其他域名访问
        arg1.addHeader("Access-Control-Allow-Origin", "*");
        // 响应类型 响应方法

        arg1.addHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");

        // 响应头设置
        arg1.addHeader("Access-Control-Allow-Headers", "Authorization,Content-Type");
        arg1.addHeader("Access-Control-Allow-Headers", "x-token");
        arg1.addHeader("Access-Control-Max-Age", "30");

        arg1.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域


        // 需要过滤的代码
        arg2.doFilter(arg0, arg1);
    }
}
