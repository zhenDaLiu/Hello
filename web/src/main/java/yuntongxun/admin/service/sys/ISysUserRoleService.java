package yuntongxun.admin.service.sys;


import com.yuntongxun.ytx.pojo.po.sys.SysUserRole;

import java.util.List;

public interface ISysUserRoleService {

    Integer insertBatch(List<SysUserRole> sysUserRoles);

    Integer deleteByUserId(Long userId);

    /**
     * 保存后台管理员角色
     * @param userId
     * @param roleCode 角色编码
     */
    void saveAdminRole(Long userId, String roleCode);
}
