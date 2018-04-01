package com.taoroot.controller.sys;

import com.taoroot.controller.BaseController;
import com.taoroot.pojo.sys.Role;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: taoroot
 * @date: 2018/3/27
 */
@Api(description = "角色管理")
@Controller
@RequestMapping("/api/v1/roles")
public class RolesController extends BaseController<Role> {
}
