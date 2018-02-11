package com.taoroot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoroot.common.*;
import com.taoroot.dao.UserMapper;
import com.taoroot.pojo.User;
import com.taoroot.service.IUserService;
import com.taoroot.util.ConfigUtil;
import com.taoroot.util.JwtUtil;
import com.taoroot.vo.NewObjCountVo;
import com.taoroot.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // 参数检查
        if (username == null || password == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), null);
        }
        if (userMapper.checkUsername(username) == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // todo  密码 MD5
        User user = userMapper.selectLogin(username, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        if (user.getStatus() == StatusTypeCode.DISABLE.getCode()) {
            return ServerResponse.createByErrorMessage("已被禁用, 请联系管理员");
        }
        String token = JwtUtil.createJWT(user.getId(), LoginTypeCode.WEB.getDesc(), "", webValidTIME);
        return ServerResponse.createBySuccessToken("登录成功", null, token);
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
            String token = JwtUtil.createJWT(user.getId(), LoginTypeCode.WEB.getDesc(), "", webValidTIME);
            return ServerResponse.createBySuccessToken(null, assembleUserVo(user), token);
        }
    }

    /**
     * 判断下用户是否是超级管理员,
     * 只有是超级管理的人才可以更新别人的信息,
     * 否则只能更新自己的信息
     *
     * @param user   待更新用户
     * @param userId 操作用户的id
     * @return
     */
    @Override
    public ServerResponse update(User user, int userId) {
        // 可以更新的用户信息
        User updateUser = new User();
        User selectUser = userMapper.selectByPrimaryKey(user.getId());
        updateUser.setId(user.getId());
        // 对用户名唯一性检测
        boolean changeNameFlag = selectUser.getUsername().equals(user.getUsername());
        if (!changeNameFlag && userMapper.checkUsername(user.getUsername()) > 0) {
            return ServerResponse.createByErrorMessage("用户名已被注册");
        }
        // 对邮箱唯一性检测
        boolean changeEmailFlag = selectUser.getEmail().equals(user.getEmail());
        if (!changeEmailFlag && userMapper.checkEmail(user.getEmail()) > 0) {
            return ServerResponse.createByErrorMessage("邮箱已被注册");
        }
        // 对手机号唯一性检测
        boolean changePhoneFlag = selectUser.getPhone().equals(user.getPhone());
        if (!changePhoneFlag && userMapper.checkPhone(user.getPhone()) > 0) {
            return ServerResponse.createByErrorMessage("改手机号已被注册");
        }
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        updateUser.setAddress(user.getAddress());

        if (userId == user.getId()) {
            return updateUser(updateUser);
        } else {
            // 只有管理员才可以更新别人的信息
            if (isAdmin(userId)) {
                updateUser.setStatus(user.getStatus());
                return updateUser(updateUser);
            } else {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_ADMIN.getCode(), null);
            }
        }
    }

    /**
     * 查看str 在 type字段中是否已被占用
     *
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse checkValid(String str, String type) {
        if (str == null || type == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), null);
        }
        switch (type) {
            case "username":
                if (userMapper.checkUsername(str) > 0) {
                    return ServerResponse.createByError();
                }
                break;
            case "email":
                if (userMapper.checkEmail(str) > 0) {
                    return ServerResponse.createByError();
                }
                break;
            default:
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), null);
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse register(User user) {
        if (userMapper.checkUsername(user.getUsername()) > 0) {
            return ServerResponse.createByErrorMessage("用户名已被注册");
        }
        if (userMapper.checkEmail(user.getEmail()) > 0) {
            return ServerResponse.createByErrorMessage("邮箱已被注册");
        }
        if (userMapper.checkPhone(user.getPhone()) > 0) {
            return ServerResponse.createByErrorMessage("改手机号已被注册");
        }
        user.setRole(1);
        user.setStatus(1);
        if (userMapper.insert(user) == 0) {
            return ServerResponse.createByErrorMessage("系统错误,注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public boolean updateStatus(int userId) {
        int status = userMapper.selectStatus(userId);
        User user = new User();
        user.setId(userId);
        if (status != StatusTypeCode.DISABLE.getCode()) {
            if (status != StatusTypeCode.ONLINE.getCode()) {
                user.setStatus(StatusTypeCode.ONLINE.getCode());
                userMapper.updateByPrimaryKeySelective(user);
            }
            return true;
        }
        return false;
    }

    @Override
    public ServerResponse search(String str, int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<User> userList = userMapper.search(str);
        PageInfo pageResult = new PageInfo(userList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse getNewUserCount(int type) {
        NewObjCountVo data = new NewObjCountVo();
        int total = 0;
        for (int i = 0; i < 7; i++) {
            data.getCount()[i] = userMapper.getNewUserCountByWeek(-1 * i - 1 + 7 * type);
            total += data.getCount()[i];
            data.setTotal(total);
        }
        return ServerResponse.createBySuccess(data);
    }

    @Override
    public boolean updatePicture(int userId, String url) {
        User user = new User();
        user.setId(userId);
        user.setAvatar(url);
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 过滤敏感信息
     *
     * @param user
     * @return
     */
    private UserVo assembleUserVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setRole(user.getRole());
        userVo.setCreateTime(user.getCreateTime());
        userVo.setUpdateTime(user.getUpdateTime());
        userVo.setPhone(user.getPhone());
        userVo.setEmail(user.getEmail());
        userVo.setUsername(user.getUsername());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    private ServerResponse updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    /**
     * 更新用户权限, 需要用户具有管理员权限,才可以进行此操作
     *
     * @param userId
     * @param level
     * @return
     */
    private ServerResponse updateRole(long userId, int level) {
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    /**
     * 判断用户身份
     *
     * @param userId
     * @return
     */
    private boolean isAdmin(int userId) {
        if (userMapper.selectRole(userId) == RoleTypeCode.ADMIN.getCode()) {
            return true;
        }
        return false;
    }
}
