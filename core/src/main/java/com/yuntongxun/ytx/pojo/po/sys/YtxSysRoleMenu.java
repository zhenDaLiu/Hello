package com.yuntongxun.ytx.pojo.po.sys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ytx_sys_role_menu 实体类 
 * @author tangxy
 * @date 2019-05-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "ytx_sys_role_menu")
public class YtxSysRoleMenu {
    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 删除状态：1-正常 2-已删除
     */
    @TableField(value = "delete_flag")
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}