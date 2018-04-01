package com.taoroot.service.sys;

import com.taoroot.vo.ServerResponse;
import com.taoroot.pojo.sys.User;
import com.taoroot.service.IBaseService;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
public interface IUserService extends IBaseService<User> {
    ServerResponse login(String userName, String password);

    ServerResponse checkValid(String str, String type);

    ServerResponse register(User user);

    ServerResponse getToken(int userId);
}
