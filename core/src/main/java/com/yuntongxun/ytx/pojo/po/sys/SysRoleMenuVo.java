package com.yuntongxun.ytx.pojo.po.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 批量插入角色菜单对象
 * @author tangxy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenuVo implements Serializable {
    private Long id;
    private Long menuId;
}