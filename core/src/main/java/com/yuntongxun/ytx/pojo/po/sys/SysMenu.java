package com.yuntongxun.ytx.pojo.po.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "ytx_sys_menu")
public class SysMenu implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 菜单代码
     */
    @TableField(value = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 菜单图标
     */
    @TableField(value = "menu_icon")
    private String menuIcon;

    /**
     * 父级菜单ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 排序号
     */
    @TableField(value = "sort_num")
    private Integer sortNum;

    /**
     * 删除状态 1-正常 0-已删除
     */
    @TableField(value = "delete_status")
    private Integer deleteStatus;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新日期
     */
    @TableField(value = "update_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}