package com.taoroot.controller;

import com.taoroot.common.ServerResponse;
import com.taoroot.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: taoroot
 * @date: 2018/2/4
 * @description: 数据控制
 */
@Controller
@RequestMapping("/api/v1/data/")
public class DataController {

    @Autowired
    private IDataService iDataService;

    /**
     * 更具数据类型, 设备id,获取到对应的数据
     * @param id 设备id
     * @param type 数据类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public ServerResponse list(int id, String type, HttpServletRequest req) {
        int userId = (int) req.getAttribute("userId");
        return iDataService.list(id, type, userId);
    }

}
