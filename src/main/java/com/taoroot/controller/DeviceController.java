package com.taoroot.controller;

import com.taoroot.common.RoleTypeCode;
import com.taoroot.common.ServerResponse;
import com.taoroot.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: taoroot
 * @date: 2018/1/29
 * @description: 设备控制器
 */
@Controller
@RequestMapping("/api/v1/device/")
public class DeviceController {

    @Autowired
    private IDeviceService iDeviceService;

    /**
     * 获取设备列表
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param title    搜索内容
     * @param orderBy  排序方式
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public ServerResponse list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                               @RequestParam(value = "title", defaultValue = "") String title,
                               @RequestParam(value = "orderBy", defaultValue = "id desc") String orderBy,
                               HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iDeviceService.getDeviceList(pageNum, pageSize, title, orderBy, userId);
    }


    @ResponseBody
    @RequestMapping(value = "list_no_page.do", method = RequestMethod.GET)
    public ServerResponse listWithPage(HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iDeviceService.getDeviceListNoPage(userId);
    }

    @ResponseBody
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse register(@RequestParam(value = "id", defaultValue = "") String id,
                                   HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iDeviceService.register(id, userId);
    }

}
