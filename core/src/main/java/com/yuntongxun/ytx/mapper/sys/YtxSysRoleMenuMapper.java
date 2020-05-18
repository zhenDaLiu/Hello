package com.yuntongxun.ytx.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntongxun.ytx.pojo.dto.rsp.sys.SysMenuRspDto;
import com.yuntongxun.ytx.pojo.po.sys.SysRoleMenuVo;
import com.yuntongxun.ytx.pojo.po.sys.YtxSysRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 通用 Mapper 代码生成器
* @author mapper-generator
*/
@Repository
public interface YtxSysRoleMenuMapper extends BaseMapper<YtxSysRoleMenu> {

    /**
     * 批量插入角色菜单
     * @param roleId
     * @param menuIds
     */
    void batchInsertRoleMenu(@Param("roleId") Long roleId, @Param("menuIds") List<SysRoleMenuVo> menuIds);

    /**
     * 根据角色id查询菜单信息
     * @param roleIds
     * @return
     */
    List<SysMenuRspDto> getUserMenusByRoles(@Param("roleIds") List<Long> roleIds);
}




