package com.yuntongxun.admin.service.sys;


import com.github.pagehelper.PageInfo;
import com.yuntongxun.ytx.pojo.dto.req.sys.*;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import com.yuntongxun.ytx.pojo.po.sys.SysRole;
import com.yuntongxun.ytx.pojo.vo.sys.SysRoleVo;

import java.util.List;

public interface ISysRoleService {

    List<SysRole> findByUserId(Long userId);

    void assign(ReqRolePermissionDto reqRolePermissionDto);

    List<SysPermission> findPermissionByRoleId(ReqIdDto reqIdDto);

    PageInfo<SysRoleVo> selectPage(ReqRolePageDto reqRolePageDto);

    Integer add(ReqRoleDto reqRoleDto);

    Integer del(ReqIdDto reqIdDto);

    Integer disable(ReqStatusDto reqStatusDto);

    /**
     * 更新角色与菜单关系
     * @param reqRoleMenuDto
     */
    void updateRoleMenu(ReqRoleMenuDto reqRoleMenuDto);

    /**
     * 检查当前用户是否是管理员角色
     * @return
     */
    SysRole checkUserIsAdmin();
}
