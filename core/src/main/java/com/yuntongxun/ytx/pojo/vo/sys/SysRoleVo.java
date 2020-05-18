package com.yuntongxun.ytx.pojo.vo.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author cc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleVo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否有效  1有效  2无效
     */
    private Integer deleteStatus;

    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 权限集合
     */
    private List<SysPermission> sysPermissions;

}