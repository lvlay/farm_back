package com.taoroot.interceptor;

import com.alibaba.fastjson.JSON;
import com.taoroot.common.ServerResponse;
import com.taoroot.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: taoroot
 * @date: 2017/11/19
 * @description: token 认证 拦截器
 */
public class SpringMVCHandlerInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<String> uncheckUrls;

    public List<String> getUncheckUrls() {
        return uncheckUrls;
    }

    public SpringMVCHandlerInterceptor setUncheckUrls(List<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
        return this;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url = httpServletRequest.getRequestURI();
        logger.info("request url ==> " + url);

        // 获取head中的Authorization
        if (httpServletRequest.getMethod().equals("OPTIONS")){
            return false;
        }

        // token认证成功就放过
        String auth = httpServletRequest.getHeader("Authorization");
        if ((auth != null) && (auth.length() > 7)) {
            Claims claims = JwtUtil.parseJWT(auth);
            if (claims != null) {
                //获取token中的用户id
                String userId = claims.get(JwtUtil.USERID).toString();
                httpServletRequest.setAttribute("userId",Integer.parseInt(userId));
                return true;
            }
        }

        httpServletResponse.setContentType("application/json; charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(10, "请重新登录");
        out.print(JSON.toJSON(serverResponse));
        out.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
