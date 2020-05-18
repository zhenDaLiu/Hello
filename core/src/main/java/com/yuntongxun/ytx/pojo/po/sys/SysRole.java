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
@TableName(value = "ytx_sys_role")
public class SysRole implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 角色编码
     */
    @TableField(value = "role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 是否有效  1有效  2无效
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