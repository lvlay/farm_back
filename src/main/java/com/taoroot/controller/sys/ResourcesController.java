package com.taoroot.controller.sys;

import com.taoroot.common.RequestMethodCode;
import com.taoroot.common.ResourceTypeCode;
import com.taoroot.vo.ServerResponse;
import com.taoroot.controller.BaseController;
import com.taoroot.pojo.sys.Resource;
import com.taoroot.service.sys.IResourceService;
import com.taoroot.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: taoroot
 * @date: 2018/3/27
 */
@Api(description = "资源（权限）控制")
@Controller
@RequestMapping("/api/v1/resources")
public class ResourcesController extends BaseController<Resource> {

    @Autowired
    private IResourceService iResourceService;

    @ApiOperation(value = "用户菜单资源", notes = "根据用户的角色，获取到对应的资源列表")
    @ResponseBody
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ServerResponse permissionList(HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        return iResourceService.getMenuByUserId(userId, ResourceTypeCode.MENU, RequestMethodCode.GET);
    }
}
