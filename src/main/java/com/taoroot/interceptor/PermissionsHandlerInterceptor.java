package com.taoroot.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.taoroot.common.RequestMethodCode;
import com.taoroot.common.ResourceTypeCode;
import com.taoroot.common.ResponseCode;
import com.taoroot.vo.ServerResponse;
import com.taoroot.pojo.sys.Resource;
import com.taoroot.service.sys.IResourceService;
import com.taoroot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/1/20
 * @description:
 */
public class PermissionsHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private IResourceService iResourceService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            return false;
        }
        int userId = (int) httpServletRequest.getAttribute(JwtUtil.USERID);
        String url = httpServletRequest.getRequestURI();
        List<Resource> list = Lists.newArrayList();

        list.addAll(iResourceService.getListByUserId(userId, ResourceTypeCode.FUNCTION, RequestMethodCode.valueOf(httpServletRequest.getMethod())));
        try {
            // 这个用包含关系去匹配
            for (Resource resource : list) {
                if (url.contains(resource.getResourceUrl())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_PERMISSION.getCode(), ("请管理员授权 [ " + url + " " + httpServletRequest.getMethod() + " ]"));
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
