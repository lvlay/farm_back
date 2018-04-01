package com.taoroot.controller.sys;

import com.taoroot.controller.BaseController;
import com.taoroot.pojo.sys.User;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author: taoroot
 * @date: 2018/1/13
 */
@Api(description = "用户管理")
@Controller
@RequestMapping("/api/v1/users")
public class UsersController extends BaseController<User> {
}
