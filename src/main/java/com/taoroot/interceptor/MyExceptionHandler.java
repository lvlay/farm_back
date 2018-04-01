package com.taoroot.interceptor;

import com.alibaba.fastjson.JSON;
import com.taoroot.common.ResponseCode;
import com.taoroot.vo.ServerResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: taoroot
 * @date: 2018/1/20
 * @description: 异常控制
 */
public class MyExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        try {
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR_BACKEND.getCode(), "后端异常");
            out.print(JSON.toJSON(serverResponse));
            out.close();
        }catch (Exception ex){

        }
        return null;
    }
}
