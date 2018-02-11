package com.taoroot.controller;

import com.google.common.collect.Maps;
import com.taoroot.common.ResponseCode;
import com.taoroot.common.ServerResponse;
import com.taoroot.pojo.User;
import com.taoroot.service.IFileService;
import com.taoroot.service.IUserService;
import com.taoroot.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description: 用户接口
 */
@Controller
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IFileService iFileService;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse login(String username, String password) {
        return iUserService.login(username, password);
    }

    /**
     * 用户退出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public ServerResponse logout() {
        return ServerResponse.createBySuccessMessage("退出成功");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "info.do", method = RequestMethod.GET)
    public ServerResponse info(HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        // 对用户状态进行检查, 如果用户状态为禁用状态, 则失败
        if (!iUserService.updateStatus(userId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "已被禁用");
        }
        return iUserService.getUserById(userId);
    }

    /**
     * 获取用户列表
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param title    搜索内容
     * @param orderBy  排序方式
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public ServerResponse getList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                  @RequestParam(value = "title", defaultValue = "") String title,
                                  @RequestParam(value = "orderBy", defaultValue = "id desc") String orderBy) {
        if (!title.equals("")) {
            return iUserService.search(title, pageNum, pageSize, orderBy);
        }
        return iUserService.getUserList(pageNum, pageSize, orderBy);
    }

    /**
     * 更新信息
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public ServerResponse update(User user, HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iUserService.update(user, userId);
    }

    /**
     * 检查用户名或邮箱是否已被占用
     *
     * @param str  带检查的字符串
     * @param type 判断检查类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "validation.do", method = RequestMethod.GET)
    public ServerResponse validation(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse register(User user) {
        return iUserService.register(user);
    }

    /**
     * 获取一个星期的新增成员数
     *
     * @param type 0表示本星期, 1表示上个星期, 以此类推
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "new_user_count.do", method = RequestMethod.GET)
    public ServerResponse new_user_count(@RequestParam(value = "type", defaultValue = "0") int type) {
        return iUserService.getNewUserCount(type);
    }


    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = ConfigUtil.get("ftp.server.http.prefix") + targetFileName;
        if (!iUserService.updatePicture(userId, targetFileName)) {
            return ServerResponse.createByErrorMessage("上传错误");
        }
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }
}
