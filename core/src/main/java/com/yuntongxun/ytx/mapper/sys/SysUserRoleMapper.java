package com.yuntongxun.ytx.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntongxun.ytx.pojo.po.sys.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色关系mapper
 * @author tangxy
 * @date 2019-07-09
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 删除用户角色
     * @param userId
     * @return
     */
    Integer deleteByUserId(@Param(value = "user_id") Long userId);

    /**
     * 插入用户角色
     * @param list
     * @return
     */
    Integer insertSysUserRoleBatch(@Param(value = "list") List<SysUserRole> list);

    /**
     * 获取后台管理网站用户所有的角色编码
     * @param userId
     * @return
     */
    List<String> getUserRoleCode(@Param("userId") Long userId);
}