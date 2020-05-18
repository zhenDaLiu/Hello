package com.yuntongxun.ytx.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限Mapper
 * @author tangxy
 * @date 2019-07-10
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 查询角色权限
     * @param roleIds
     * @return
     */
    List<SysPermission> findByRoleIds(@Param(value = "role_ids") List<Long> roleIds);

    /**
     * 根据角色名称，权限名称查询
     * @param menuName
     * @param permissionName
     * @return
     */
    List<SysPermission> selectPage(@Param(value = "menu_name") String menuName, @Param(value = "permission_name") String permissionName);

    /**
     * 根据权限编码查询
     * @param permissionCode
     * @return
     */
    SysPermission findOneByPermissionCode(@Param(value = "permission_code") String permissionCode);

    /**
     * 根据权限名称查询
     * @param permissionName
     * @return
     */
    SysPermission findOneByPermissionName(@Param(value = "permission_name") String permissionName);
}