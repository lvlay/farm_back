package com.taoroot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoroot.common.ServerResponse;
import com.taoroot.dao.DeviceMapper;
import com.taoroot.dao.GreenhouseMapper;
import com.taoroot.pojo.Device;
import com.taoroot.pojo.Greenhouse;
import com.taoroot.service.IGreenhouseService;
import com.taoroot.vo.GreenhouseDeviceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/2/10
 * @description:
 */
@Service("IGreenhouseService")
public class GreenhouseServiceImpl implements IGreenhouseService {

    @Autowired
    private GreenhouseMapper greenhouseMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ServerResponse add(Greenhouse greenhouse, int userId) {
        if (greenhouse.getName().equals("")) {
            return ServerResponse.createByErrorMessage("添加失败");
        }
        greenhouse.setUserId(userId);
        int result = greenhouseMapper.insert(greenhouse);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }


    @Override
    public ServerResponse getList(int pageNum, int pageSize, String title, String orderBy, int userId) {
        PageHelper.startPage(pageNum, pageSize, orderBy);

        List<Greenhouse> greenhouses;
        greenhouses = greenhouseMapper.selectListByUser(title, userId);
        List<GreenhouseDeviceVo> list = new ArrayList<>();
        // 遍历得到大棚具有的设备
        for (Greenhouse g : greenhouses) {
            List<Device> devices = deviceMapper.selectListByGreenhouse(g.getId());
            list.add(new GreenhouseDeviceVo(g, devices));
        }

        PageInfo pageResult = new PageInfo(greenhouses);
        pageResult.setList(list);

        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse getListNoPage(int userId) {
        List<Greenhouse> greenhouses;
        greenhouses = greenhouseMapper.selectListByUser("", userId);

        List<GreenhouseDeviceVo> list = new ArrayList<>();
        // 遍历得到大棚具有的设备
        for (Greenhouse g : greenhouses) {
            List<Device> devices = deviceMapper.selectListByGreenhouse(g.getId());
            list.add(new GreenhouseDeviceVo(g, devices));
        }

        return ServerResponse.createBySuccess(list);
    }
}
