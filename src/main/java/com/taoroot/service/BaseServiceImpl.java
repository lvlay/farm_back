package com.taoroot.service;

import com.google.common.collect.Lists;
import com.taoroot.vo.ServerResponse;
import com.taoroot.dao.BaseMapper;
import com.taoroot.dao.BaseSql;
import com.taoroot.util.ReflectionUtil;
import com.taoroot.util.StaticUtil;
import com.taoroot.vo.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: taoroot
 * @date: 2018/3/25
 * @description: 基本Service
 */
@Service("IBaseService")
public class BaseServiceImpl<T> implements IBaseService<T> {

    private final static Logger logger = LoggerFactory.getLogger(BaseSql.class);

    @Autowired
    public BaseMapper<T> baseMapper;

    @Override
    public ServerResponse add(T t, int userId) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        int result = 0;

        updateCreateTimeParams(t);
        updateCreateUserIdParams(t, userId);

        try {
            result = baseMapper.insert(t, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return ServerResponse.createBySuccess("创建成功");
        }
        return ServerResponse.createByErrorMessage("创建失败");
    }

    @Override
    public ServerResponse delete(int id, int userId) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        baseMapper.delete(id, c);
        return ServerResponse.createByErrorMessage("删除成功");
    }

    @Override
    public ServerResponse update(T t, int userId) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        int result = 0;
        try {
            result = baseMapper.update(t, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return ServerResponse.createByErrorMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse get(int id, int userId) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T t = baseMapper.getById(id, c);
        return ServerResponse.createBySuccess(t);
    }

    @Override
    public ServerResponse grid(int page, int rows, Map<String, Object> paramMap, Map<String, String> orderMap, int userId) {
        updateParams(paramMap);
        List<T> list = Lists.newArrayList();
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if ((rows == 0) && paramMap.size() == 0 && orderMap.size() == 0) {
            list.addAll(baseMapper.find(c));
        } else if (paramMap.size() > 0 && orderMap.size() == 0 && rows == 0) {
            list.addAll(baseMapper.findParam(paramMap, c));
        } else if (paramMap.size() > 0 && orderMap.size() == 0 && rows != 0) {
            list.addAll(baseMapper.findParamAndPage(paramMap, page, rows, c));
        } else if (paramMap.size() > 0 && orderMap.size() > 0 && rows == 0) {
            list.addAll(baseMapper.findParamAndOrder(paramMap, c, orderMap));
        } else if (paramMap.size() > 0 && orderMap.size() > 0 && rows != 0) {
            list.addAll(baseMapper.findParamAndOrderAndPage(paramMap, page, rows, c, orderMap));
        } else if (paramMap.size() == 0 && orderMap.size() == 0 && rows != 0) {
            list.addAll(baseMapper.findPage(c, page, rows));
        } else if (paramMap.size() == 0 && orderMap.size() > 0 && rows == 0) {
            list.addAll(baseMapper.findOrder(c, orderMap));
        } else if (paramMap.size() == 0 && orderMap.size() > 0 && rows != 0) {
            list.addAll(baseMapper.findPageAndOrder(c, page, rows, orderMap));
        }
        long total;
        if (paramMap.size() > 0) {
            total = count(paramMap);
        } else {
            total = count();
        }
        long records = total;
        total = total % rows == 0 ? total / rows : total / rows + 1;
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setPage(page);
        grid.setTotal(total);
        grid.setRecords(records);
        return ServerResponse.createBySuccess("获取成功", grid);
    }

    @Override
    public ServerResponse list(int userId) {
        return null;
    }

    @Override
    public ServerResponse logicDelete(int id, int userId) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        int result = 0;
        try {
            result = baseMapper.logicDelete(id, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return ServerResponse.createBySuccess("操作成功");
        }
        return ServerResponse.createByErrorMessage("操作失败");
    }

    @Override
    public ServerResponse logicDeleteBatch(List<Object> ids, long userId) {
        return null;
    }

    @Override
    public ServerResponse tree(long userId) {
        return null;
    }

    @Override
    public long count() {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.count(c);
    }

    @Override
    public long count(Map<String, Object> map) {
        updateParams(map);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.countParam(map, c);
    }

    @Override
    public List<T> find() {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.find(c);
    }

    @Override
    public List<T> find(Map<String, String> order) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findOrder(c, order);
    }

    @Override
    public List<T> find(Map<String, String> order, int page, int rows) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findPageAndOrder(c, page, rows, order);
    }

    @Override
    public List<T> find(int page, int rows) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findPage(c, page, rows);
    }

    @Override
    public List<T> findParams(Map<String, Object> params) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findParam(params, c);
    }

    @Override
    public List<T> findParams(Map<String, Object> params, Map<String, String> order) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findParamAndOrder(params, c, order);
    }

    @Override
    public List<T> findParams(Map<String, Object> params, int page, int rows) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findParamAndPage(params, page, rows, c);
    }

    @Override
    public List<T> findParams(Map<String, Object> params, Map<String, String> order, int page, int rows) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findParamAndOrderAndPage(params, page, rows, c, order);
    }


    /**
     * 更新一下like参数值
     *
     * @param params
     */
    private void updateParams(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String[] conditions = entry.getKey().split(StaticUtil.SQL_SPLIT);
            String condition = (conditions.length > 1 ? conditions[1].toUpperCase() : conditions[0].toLowerCase());
            switch (condition) {
                case "LK":
                    params.put(entry.getKey(), "%" + entry.getValue() + "%");
                    break;
                case "RLK":
                    params.put(entry.getKey(), entry.getValue() + "%");
                    break;
                case "LLK":
                    params.put(entry.getKey(), "%" + entry.getValue());
                    break;
            }
        }
    }

    /**
     * 添加创建日期参数
     */
    private void updateCreateTimeParams(T t) {
        Field createTime = ReflectionUtil.getDeclaredField(t, "createTime");
        if (createTime != null) {
            try {
                createTime.setAccessible(true);
                createTime.set(t, new Date()); // 设置创建日期
            } catch (IllegalAccessException e) {
                logger.error("isFieldNull: {}", e.getMessage());
            }
        }
    }


    /**
     * 添加创建用户参数
     */
    private void updateCreateUserIdParams(T t, int userId) {
        Field CreateUserId = ReflectionUtil.getDeclaredField(t, "createUserId");
        if (CreateUserId != null) {
            try {
                CreateUserId.setAccessible(true);
                CreateUserId.set(t, userId); // 设置创建用户Id
            } catch (IllegalAccessException e) {
                logger.error("isFieldNull: {}", e.getMessage());
            }
        }
    }

}
