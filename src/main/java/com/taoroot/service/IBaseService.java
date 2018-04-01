package com.taoroot.service;

import com.taoroot.vo.ServerResponse;

import java.util.List;
import java.util.Map;

/**
 * @author: taoroot
 * @date: 2018/3/25
 * @description: 基本 Service
 */
public interface IBaseService<T> {

    ServerResponse add(T t, int userId);

    ServerResponse delete(int id, int userId);

    ServerResponse update(T t, int userId);

    ServerResponse get(int id, int userId);

    ServerResponse grid(int page, int rows, Map<String, Object> paramMap, Map<String, String> orderMap, int userId);

    ServerResponse list(int userId);

    ServerResponse logicDelete(int id, int userId);

    ServerResponse logicDeleteBatch(List<Object> ids, long userId);

    ServerResponse tree(long userId);


    /**
     * 获取数量
     *
     * @return
     */
    long count();


    /**
     * 获取数量
     *
     * @param params 查询条件
     * @return
     */
    long count(Map<String, Object> params);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> find();

    /**
     * 查询所有数据并排序
     *
     * @param order key 属性名(列名)  value  asc/desc
     * @return
     */
    List<T> find(Map<String, String> order);

    /**
     * 分页查询 排序
     *
     * @param order key 属性名(列名)  value  asc/desc
     * @param page  页数
     * @param rows  查询一页的条数
     * @return
     */
    List<T> find(Map<String, String> order, int page, int rows);

    /**
     * @param page 页数
     * @param rows 查询一页的条数
     * @return
     */
    List<T> find(int page, int rows);

    /**
     * @param params key->value key field_symbol  demo(username_=)查询用户名等于 value
     * @return
     */
    List<T> findParams(Map<String, Object> params);

    /**
     * 根据条件查询
     *
     * @param params key->value key field_symbol  demo(username_=)查询用户名等于 value
     * @param order  排序字段 升降  key->value(column+desc/asc)或者(column)
     * @return
     */
    List<T> findParams(Map<String, Object> params, Map<String, String> order);

    /**
     * 根据条件和分页查询
     *
     * @param params
     * @param page
     * @param rows
     * @return
     */
    List<T> findParams(Map<String, Object> params, int page, int rows);

    /**
     * 根据条件、排序、分页查询
     *
     * @param params key->value key field_symbol  demo(username_=)查询用户名等于 value
     * @param order  排序字段 升降  key->value(column+desc/asc)或者(column)
     * @param page   页数
     * @param rows   查询一页的条数
     * @return
     */
    List<T> findParams(Map<String, Object> params, Map<String, String> order, int page, int rows);

}
