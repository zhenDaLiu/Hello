package com.yuntongxun.ytx.pojo.po.sys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "ytx_sys_permission")
public class SysPermission implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 归属菜单,前端判断并展示菜单使用
     */
    @TableField(value = "menu_code")
    private String menuCode;

    /**
     * 菜单的中文释义
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 权限的代码/通配符,对应代码中@RequiresPermissions 的value
     */
    @TableField(value = "permission_code")
    private String permissionCode;

    /**
     * 本权限的中文释义
     */
    @TableField(value = "permission_name")
    private String permissionName;

    /**
     * 是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选
     */
    @TableField(value = "required_permission")
    private Integer requiredPermission;

}