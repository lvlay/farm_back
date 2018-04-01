package com.taoroot.service.sys.impl;

import com.google.common.collect.Maps;
import com.taoroot.common.*;
import com.taoroot.pojo.sys.User;
import com.taoroot.service.BaseServiceImpl;
import com.taoroot.service.sys.IUserService;
import com.taoroot.util.ConfigUtil;
import com.taoroot.util.JwtUtil;
import com.taoroot.vo.ServerResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
@Service("IUserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    public static final Long appValidTIME = Long.valueOf(ConfigUtil.get("appValidTIME"));
    public static final Long webValidTIME = Long.valueOf(ConfigUtil.get("webValidTIME"));

    @Override
    public ServerResponse login(String userName, String password) {
        // 参数检查
        if (userName == null || password == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), null);
        }
        if (!checkUsername(userName)) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // todo  密码 MD5
        User user = selectLogin(userName, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        if (user.getStatus() == StatusTypeCode.DISABLE.getCode()) {
            return ServerResponse.createByErrorMessage("已被禁用, 请联系管理员");
        }
        String token = JwtUtil.createJWT(user.getId(), ResourceKindCode.WEB.getDesc(), "", webValidTIME);
        return ServerResponse.createBySuccessToken("登录成功", null, token);
    }


    @Override
    public ServerResponse checkValid(String str, String type) {
        if (str == null || type == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), null);
        }
        switch (type) {
            case "userName":
                if (!checkUsername(str)) {
                    return ServerResponse.createByErrorMessage("用户名不存在");
                }
                break;
            case "email":
                if (!checkEmail(str)) {
                    return ServerResponse.createByErrorMessage("邮箱存在");
                }
                break;
            default:
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), null);
        }
        return ServerResponse.createBySuccessMessage("验证通过");
    }

    @Override
    public ServerResponse register(User user) {
        User registerUser = new User();
        // 用户名检查
        if (user.getUserName() == null) {
            return ServerResponse.createByErrorMessage("用户名不能为空");
        }
        if (checkUsername(user.getUserName())) {
            return ServerResponse.createByErrorMessage("用户名已被注册");
        }
        // 密码检查
        if (user.getPassword() == null) {
            return ServerResponse.createByErrorMessage("密码不能为空");
        }
        if (user.getPassword().length() < 6) {
            return ServerResponse.createByErrorMessage("密码长度不能小于6位");
        }
        // 邮箱检查
        if (user.getEmail() == null) {
            return ServerResponse.createByErrorMessage("邮箱不能为空");
        }
        if (checkEmail(user.getEmail())) {
            return ServerResponse.createByErrorMessage("邮箱已被注册");
        }
        registerUser.setUserName(user.getUserName());
        registerUser.setPassword(user.getPassword());
        registerUser.setEmail(user.getEmail());
        return add(registerUser, 0);
    }

    @Override
    public ServerResponse getToken(int userId) {
        String token = JwtUtil.createJWT(userId, ResourceKindCode.WEB.getDesc(), "", webValidTIME);
        return ServerResponse.createBySuccessToken("更新成功", null, token);
    }

    private boolean checkUsername(String userName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("user_name_EQ", userName);
        params.put("status_EQ", StatusTypeCode.ENABLE.getCode());
        List<User> users = findParams(params);
        return users.size() > 0;
    }

    private boolean checkEmail(String email) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("email_EQ", email);
        params.put("status_EQ", StatusTypeCode.ENABLE.getCode());
        List<User> users = findParams(params);
        return users.size() > 0;
    }


    private User selectLogin(String userName, String password) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("user_name_EQ", userName);
        params.put("password_EQ", password);
        params.put("status_EQ", StatusTypeCode.ENABLE.getCode());
        List<User> users = findParams(params);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

}
