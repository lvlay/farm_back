package com.taoroot.dao.sys;

import com.taoroot.dao.BaseMapper;
import com.taoroot.pojo.sys.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/3/27
 * @description: 资源 Dao 文件
 */
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     *
     * @param userId 用户
     * @param kind  资源类型
     * @param type  资源设备类型
     * @param method http 请求类型
     * @return 与用户关联的角色具有的资源列表
     */
    @Select(value = "SELECT resource.* FROM tb_role_user role_user LEFT JOIN tb_user user ON role_user.user_id = user.id LEFT JOIN tb_role role ON role.id = role_user.role_id LEFT JOIN tb_role_resource role_resource ON role.id = role_resource.role_id  LEFT JOIN tb_resource resource ON role_resource.resource_id = resource.id WHERE resource.status = 0 AND user.id = #{user_id} AND resource.resource_kind = #{kind} AND resource.resource_type = #{type} AND resource.resource_method = #{method} ")
    List<Resource> getListByUserId(@Param("user_id") int userId, @Param("kind") String kind, @Param("type") int type, @Param("method") String method);

    /**
     *
     * @param userId 用户
     * @param kind  资源类型
     * @param type  资源设备类型
     * @param method http 请求类型
     * @param
     * @return 与用户关联的角色具有的资源列表
     */
    @Select(value = "SELECT resource.* FROM tb_role_user role_user LEFT JOIN tb_user user ON role_user.user_id = user.id LEFT JOIN tb_role role ON role.id = role_user.role_id LEFT JOIN tb_role_resource role_resource ON role.id = role_resource.role_id  LEFT JOIN tb_resource resource ON role_resource.resource_id = resource.id WHERE resource.status = 0 AND user.id = #{user_id} AND resource.resource_kind = #{kind} AND resource.resource_type = #{type} AND resource.resource_method = #{method} AND resource.resource_parent_id = #{parent} ")
    List<Resource> getListByUserIdParentId(@Param("user_id") int userId, @Param("kind") String kind, @Param("type") int type, @Param("method") String method, @Param("parent") Integer parentId);
}
