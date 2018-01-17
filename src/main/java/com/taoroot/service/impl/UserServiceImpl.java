package com.taoroot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoroot.common.LoginTypeCode;
import com.taoroot.common.ServerResponse;
import com.taoroot.dao.UserMapper;
import com.taoroot.pojo.User;
import com.taoroot.service.IUserService;
import com.taoroot.util.ConfigUtil;
import com.taoroot.util.JwtUtil;
import com.taoroot.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
@Service("IUserService")
public class UserServiceImpl implements IUserService {

    public static final Long appValidTIME = Long.valueOf(ConfigUtil.get("appValidTIME"));
    public static final Long webValidTIME = Long.valueOf(ConfigUtil.get("webValidTIME"));

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // todo  密码 MD5
        User user = userMapper.selectLogin(username, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        String token = JwtUtil.createJWT(user.getId(), LoginTypeCode.WEB.getDesc(), "", webValidTIME);
        return ServerResponse.createBySuccessToken("登录成功", assembleUserVo(user), token);
    }

    @Override
    public ServerResponse<PageInfo> getUserList(int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<User> userList = userMapper.selectList();
        PageInfo pageResult = new PageInfo(userList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse getUserById(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByError();
        } else {
            return ServerResponse.createBySuccess(user);
        }
    }

    @Override
    public ServerResponse update(User user, int userId) {
        // 判断下用户是否是超级管理员, 只有是超级管理的人才可以更新别人的信息, 否则只能更新自己的信息
        User updateUser = new User();
        updateUser.setId(user.getId());
        // todo 对用户名唯一性检测
        updateUser.setUsername(user.getUsername());
        updateUser.setQuestion(user.getQuestion());
        // todo 对邮箱唯一性检测
        updateUser.setEmail(user.getEmail());
        updateUser.setAnswer(user.getAnswer());
        updateUser.setAddress(user.getAddress());
        // todo 对手机号唯一性检测
        updateUser.setPhone(user.getPhone());

        if (userId == user.getId()) {
            return updateUser(updateUser);
        } else {
            User user0 = userMapper.selectByPrimaryKey(userId);
            // todo 建立一个枚举,代替0
            // 只有管理员才可以更新别人的信息
            if (user0.getRole() == 0) {
                return updateUser(updateUser);
            } else {
                return ServerResponse.createByErrorMessage("权限不足");
            }
        }
    }


    private UserVo assembleUserVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setRole(user.getRole());
        userVo.setCreateTime(user.getCreateTime());
        userVo.setUpdateTime(user.getUpdateTime());
        userVo.setPhone(user.getPhone());
        userVo.setUsername(user.getUsername());
        return userVo;
    }

    private ServerResponse updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return ServerResponse.createBySuccessMessage("更新成功");
    }
}
