package com.taoroot.controller.sys;

import com.taoroot.common.ResponseCode;
import com.taoroot.vo.ServerResponse;
import com.taoroot.pojo.sys.User;
import com.taoroot.service.sys.IUserService;
import com.taoroot.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: taoroot
 * @date: 2018/3/25
 */

@Api(description = "登录、注册相关接口")
@Controller
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private IUserService iUserService;


    @ApiOperation(value = "登录", notes = "用 用户名 和 密码 进行登录，获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse login(@RequestBody User user) {
        return iUserService.login(user.getUserName(), user.getPassword());
    }


    @ApiOperation(value = "更新 token", notes = "定时更新 token，防止 token 过期")
    @ResponseBody
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public ServerResponse checkSession(HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        return iUserService.getToken(userId);
    }


    @ApiOperation(value = "可用性验证", notes = "检查用户名或邮箱是否已被占用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "带检查的字符串", required = true, dataType = "string"),
            @ApiImplicitParam(name = "type", value = "判断检查类型", required = true, dataType = "string")
    })
    @ResponseBody
    @RequestMapping(value = "/validation", method = RequestMethod.GET)
    public ServerResponse validation(String str, String type) {
        return iUserService.checkValid(str, type);
    }


    @ApiOperation(value = "用户信息", notes = "登录用户的详情")
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ServerResponse info(HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        return iUserService.get(userId, userId);
    }


    @ApiOperation(value = "用户退出", notes = "用户退出，清空缓存中的token")
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ServerResponse logout() {
        return ServerResponse.createBySuccessMessage("退出成功");
    }


    @ApiOperation(value = "注册用户", notes = "新用户通过自己注册账号")
    @ApiImplicitParam(name = "user", value = "带有注册信息的用户对象", required = true, dataType = "user")
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ServerResponse register(User user) {
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        return iUserService.register(user);
    }

}
