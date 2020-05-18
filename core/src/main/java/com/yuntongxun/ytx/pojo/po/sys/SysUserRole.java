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
@TableName(value = "ytx_sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 账户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

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

    public SysUserRole(Long userId, Long roleId, Date createTime, Date updateTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}