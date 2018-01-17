package com.taoroot.controller;

import com.taoroot.common.ServerResponse;
import com.taoroot.pojo.User;
import com.taoroot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
@Controller
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse login(String username, String password) {
        return iUserService.login(username, password);
    }

    /**
     * 用户退出
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    public ServerResponse logout() {
        return ServerResponse.createBySuccessMessage("退出成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "info.do", method = RequestMethod.GET)
    public ServerResponse info(HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iUserService.getUserById(userId);
    }

    /**
     * 获取用户列表
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public ServerResponse getList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5")int pageSize, @RequestParam(value = "orderBy", defaultValue = "id desc")String orderBy) {
        return iUserService.getUserList(pageNum, pageSize, orderBy);
    }

    /**
     * 更新信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public ServerResponse update(User user, HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iUserService.update(user, userId);
    }

}
