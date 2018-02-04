package com.taoroot.service;

import com.taoroot.common.ServerResponse;
import com.taoroot.pojo.User;
import com.taoroot.vo.UserVo;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
public interface IUserService {

    ServerResponse login(String username, String password);

    ServerResponse getUserList(int pageNum, int pageSize, String orderBy);

    ServerResponse getUserById(int userId);

    ServerResponse update(User user, int userId);

    ServerResponse checkValid(String str, String type);

    ServerResponse register(User user);

    boolean updateStatus(int userId);

    ServerResponse search(String str, int pageNum, int pageSize, String orderBy);

    ServerResponse getNewUserCount(int type);
}
