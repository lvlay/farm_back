package com.taoroot.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 方法不允许重载不然会异常出错
 *
 * @param <T>
 */
@Repository
public interface BaseMapper<T> {

    @UpdateProvider(type = BaseSql.class, method = "update")
    public int update(@Param("object") T object, Class<T> t);

    //必须传t object 默认会被转化成map
    @InsertProvider(type = BaseSql.class, method = "insert")
    @Options(keyColumn = "id")
    public int insert(@Param("object") T object, Class<T> t);

    @DeleteProvider(type = BaseSql.class, method = "deleteBatch")
    public int deleteBatch(@Param("ids") String ids, Class<T> t);

    @SelectProvider(type = BaseSql.class, method = "getById")
    public T getById(@Param("id") int id, Class<T> t);

    @DeleteProvider(type = BaseSql.class, method = "delete")
    public int delete(@Param("id") int id, Class<T> t);

    @SelectProvider(type = BaseSql.class, method = "findSQL")
    public List<T> find(Class<T> t);

    @SelectProvider(type = BaseSql.class, method = "findOrderSQL")
    public List<T> findOrder(final Class<T> t, @Param("order") Map<String, String> order);

    @SelectProvider(type = BaseSql.class, method = "findPageSQL")
    public List<T> findPage(final Class<T> t, @Param("page") int page, @Param("rows") int rows);

    @SelectProvider(type = BaseSql.class, method = "findPageAndOrderSQL")
    public List<T> findPageAndOrder(final Class<T> t, @Param("page") int page, @Param("rows") int rows, @Param("order") Map<String, String> order);

    @SelectProvider(type = BaseSql.class, method = "findParamSQL")
    public List<T> findParam(@Param("params") Map<String, Object> params, Class<T> t);

    /**
     * 带参数和排序的查询
     *
     * @param params
     * @param t
     * @param order
     * @return
     */
    @SelectProvider(type = BaseSql.class, method = "findParamAndOrderSQL")
    public List<T> findParamAndOrder(@Param("params") Map<String, Object> params, Class<T> t, @Param("order") Map<String, String> order);

    /**
     * 带参数和分页的查询
     *
     * @param params
     * @param page
     * @param rows
     * @param t
     * @return
     */
    @SelectProvider(type = BaseSql.class, method = "findParamAndPageSQL")
    public List<T> findParamAndPage(@Param("params") Map<String, Object> params, @Param("page") int page, @Param("rows") int rows, Class<T> t);

    /**
     * 带参数和排序、分页查询
     *
     * @param params
     * @param t
     * @param order
     * @return
     */
    @SelectProvider(type = BaseSql.class, method = "findParamAndOrderAndPageSQL")
    public List<T> findParamAndOrderAndPage(@Param("params") Map<String, Object> params, @Param("page") int page, @Param("rows") int rows, Class<T> t, @Param("order") Map<String, String> order);


    /**
     * 获取所有数据数量
     *
     * @param t
     * @return
     */
    @SelectProvider(type = BaseSql.class, method = "countSql")
    public long count(Class<T> t);

    /**
     * 根据条件获取数据数量
     *
     * @param params
     * @param t
     * @return
     */
    @SelectProvider(type = BaseSql.class, method = "countParamsSQL")
    public long countParam(@Param("params") Map<String, Object> params, Class<T> t);

    /**
     * 逻辑删除
     *
     * @param id
     * @param t
     * @return
     */
    @UpdateProvider(type = BaseSql.class, method = "logicDelete")
    public int logicDelete(@Param("id") int id, Class<T> t);

    /**
     * 批量逻辑删除
     *
     * @param ids
     * @param t
     * @return
     */
    @UpdateProvider(type = BaseSql.class, method = "deleteLogicBatch")
    public int deleteLogicBatch(@Param("ids") String ids, Class<T> t);

    @SelectProvider(type = BaseSql.class, method = "findParamTreeGridSQL")
    public List<T> findTreeGridByParams(@Param("parentColumn") String parentColumn, final Class<T> t, boolean showDelete);
}
