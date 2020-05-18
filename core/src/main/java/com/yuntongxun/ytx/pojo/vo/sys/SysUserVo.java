package com.yuntongxun.ytx.pojo.vo.sys;

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
public class SysUserVo implements Serializable {
    private static final long serialVersionUID = -184829892656379754L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 账户名称
     */
    private String username;

    /**
     * 数据中心
     */
    private String dcId;

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
     * 角色列表
     */
    private String roles;

}