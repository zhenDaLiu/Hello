package com.yuntongxun.ytx.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import com.yuntongxun.ytx.pojo.po.sys.SysRole;
import com.yuntongxun.ytx.pojo.po.sys.SysRolePermission;
import com.yuntongxun.ytx.pojo.vo.sys.SysRoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色Mapper
 * @author tangxy
 * @date 2019-07-10
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户编码查询用户ID
     * @param userId
     * @return
     */
    List<SysRole> findByUserId(@Param(value = "user_id") Long userId);

    /**
     * 清空角色权限
     * @param roleId
     * @return
     */
    Integer clearPermission(@Param(value = "role_id") Long roleId);

    /**
     *  赋予权限
     * @param sysRolePermissions
     * @return
     */
    Integer givePermission(@Param(value = "list") List<SysRolePermission> sysRolePermissions);

    /**
     * 获取角色权限列表
     * @param roleId
     * @return
     */
    List<SysPermission> findPermissionByRoleId(@Param(value = "role_id") Long roleId);

    /**
     * 更新权限更新类型
     * @param id
     * @param deleteStatus
     * @return
     */
    Integer updateStatusById(@Param(value = "id") Long id, @Param(value = "delete_status") Integer deleteStatus);

    /**
     * 查询角色列表
     * @param startRow
     * @param offsetLimit
     * @param roleName
     * @return
     */
    List<SysRoleVo> selectPList(
            @Param(value = "start_row") Integer startRow,
            @Param(value = "offset_limit") Integer offsetLimit,
            @Param(value = "role_name") String roleName
    );

    /**
     * 根据角色名称查询记录数量
     * @param roleName
     * @return
     */
    Integer selectPCount(
            @Param(value = "role_name") String roleName
    );

    /**
     * 根据角色名称查询角色信息
     * @param roleName
     * @return
     */
    SysRole selectOneByRoleName(@Param(value = "role_name") String roleName);

    /**
     * 检查用户是否是管理员
     * @param roleCode
     * @param username
     * @return
     */
    SysRole checkUserIsAdmin(@Param(value = "roleCode") String roleCode, @Param(value = "username") String username);
}