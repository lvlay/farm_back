package com.taoroot.interceptor;

import com.alibaba.fastjson.JSON;
import com.taoroot.common.ResponseCode;
import com.taoroot.common.ServerResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: taoroot
 * @date: 2018/1/20
 * @description: todo 权限控制, 用户被禁用
 */
public class PermissionsHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return false;
        }
        int userId = (int) request.getAttribute("userId");
        String url = request.getRequestURI();

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_ADMIN.getCode(), "请向管理员索取需要的权限");
        out.print(JSON.toJSON(serverResponse));
        out.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
