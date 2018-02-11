package com.taoroot.controller;

import com.taoroot.common.ServerResponse;
import com.taoroot.pojo.Greenhouse;
import com.taoroot.service.IGreenhouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: taoroot
 * @date: 2018/2/10
 * @description:
 */
@Controller
@RequestMapping("/api/v1/greenhouse/")
public class GreenhouseController {

    @Autowired
    private IGreenhouseService iGreenhouseService;

    /**
     * 添加大棚
     * @param name
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public ServerResponse add(String name, HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iGreenhouseService.add(new Greenhouse(name), userId);
    }

      /**
     * 获取大棚列表
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
        return iGreenhouseService.getList(pageNum, pageSize, title, orderBy, userId);
    }

    @ResponseBody
    @RequestMapping(value = "list_no_page.do", method = RequestMethod.GET)
    public ServerResponse listWithPage(HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iGreenhouseService.getListNoPage(userId);
    }
}
