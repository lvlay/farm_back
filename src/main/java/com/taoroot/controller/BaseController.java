package com.taoroot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.taoroot.common.ResponseCode;
import com.taoroot.vo.ServerResponse;
import com.taoroot.service.IBaseService;
import com.taoroot.util.JwtUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.Map;


/**
 * @author: taoroot
 * @date: 2018/3/25
 * @description: 基类 控制类
 */
public class BaseController<T> {

    @Autowired
    protected IBaseService<T> iBaseService;


    /**
     * 创建 资源
     *
     * @return
     */
    @ApiOperation(value = "创建 资源", notes = "根据 json 中的字段信息创建资源")
    @ApiImplicitParam(name = "t", value = "创建资源对象", required = true, dataType = "Object")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ServerResponse save(@RequestBody T t, HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        return iBaseService.add(t, userId);
    }

    /**
     * 根据 id 查询 资源
     *
     * @return
     */
    @ApiOperation(value = "根据 id 查询 资源", notes = "根据 url 的 id 来指定查询资源")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "integer")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ServerResponse get(@PathVariable("id") Integer id, HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        return iBaseService.get(id, userId);
    }

    /**
     * 根据 id 删除 资源
     *
     * @return
     */

    @ApiOperation(value = "根据 id 删除 资源", notes = "根据 url 的 id 来指定删除资源")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "integer")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ServerResponse delete(@PathVariable("id") Integer id, HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        if (id == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        return iBaseService.delete(id, userId);
    }

    /**
     * 更新 资源
     *
     * @return
     */
    @ApiOperation(value = "根据 id 更新 资源", notes = "根据 url 的 id 来指定更新对象，并根据传过来的资源信息更新资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "integer"),
            @ApiImplicitParam(name = "t", value = "创建资源对象", required = true, dataType = "Object")
    })
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ServerResponse update(@PathVariable("id") Integer id, @RequestBody T t, HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        if (id == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        return iBaseService.update(t, userId);
    }


    /**
     * 列表查询 资源列表
     *
     * @return
     */
    @ApiOperation(value = "列表 查询", notes = "列表查询 资源列表")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse list(HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        return iBaseService.list(userId);
    }

    /**
     * 分页查询 资源列表
     *
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @ApiOperation(value = "分页 查询", notes = "分页查询 资源列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页号", dataType = "integer", defaultValue = "1"),
            @ApiImplicitParam(name = "page_size", value = "行数", dataType = "integer", defaultValue = "5"),
            @ApiImplicitParam(name = "order", value = "排序方式", dataType = "String", defaultValue = "id_desc")
    })
    @ResponseBody
    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    public ServerResponse grid(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "rows", defaultValue = "5") int rows,
                               @RequestParam(value = "params", defaultValue = "{}") String params,
                               @RequestParam(value = "order", defaultValue = "") String order,
                               HttpServletRequest req) {
        Map<String, Object> paramMap = Maps.newHashMap();
        Map<String, String> orderMap = Maps.newHashMap();
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        if (params != null && !"".equals(params)) {
            paramMap.putAll(JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
            }));
        }
        if (order != null && !"".equals(order)) {
            orderMap.put(order, order);
        }
        return iBaseService.grid(page, rows, paramMap, orderMap, userId);
    }

    /**
     * 树形查询 资源列表
     *
     * @return
     */
    @ApiOperation(value = "树形 查询", notes = "树形查询 资源列表")
    @ResponseBody
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ServerResponse tree(HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        return iBaseService.tree(userId);
    }

    /**
     * 根据 id 逻辑 删除
     *
     * @return
     */

    @ApiOperation(value = "根据 id 逻辑删除 资源", notes = "根据 url 的 id 来指定逻辑删除资源")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "integer")
    @ResponseBody
    @RequestMapping(value = "/logic/{id}", method = RequestMethod.DELETE)
    public ServerResponse logicDelete(@PathVariable("id") Integer id, HttpServletRequest req) {
        int userId = (int) req.getAttribute(JwtUtil.USERID);
        if (id == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数错误");
        }
        return iBaseService.logicDelete(id, userId);
    }

//    /**
//     * 批量逻辑删除
//     *
//     * @return
//     */
//    @ResponseBody
//    @ApiOperation(value = "批量逻辑 删除", notes = "批量逻辑删除 资源列表")
//    @RequestMapping(value = "/logic_batch", method = RequestMethod.DELETE)
//    @ApiImplicitParam(name = "ids", value = "资源 id 数组", required = true, dataType = "array")
//    public ServerResponse logicDeleteBatch(@RequestParam(value = "ids") List<Object> ids, HttpServletRequest req) {
//        int userId = (int) req.getAttribute(JwtUtil.USERID);
//        return iBaseService.logicDeleteBatch(ids, userId);
//    }

    /**
     * 用 json 根据泛型的实现类，反序列化给实现类赋值
     *
     * @param json
     * @return
     */
    private T getClassByT(String json) {
        // 获取泛型的具体实现类
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        // 反序列化
        return JSON.parseObject(json, c);
    }
}
