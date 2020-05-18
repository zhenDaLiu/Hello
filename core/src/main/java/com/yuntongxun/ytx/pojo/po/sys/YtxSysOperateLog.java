package com.yuntongxun.ytx.pojo.po.sys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * ytx_sys_operate_log 实体类 
 * @author tangxy
 * @date 2019-07-19
 */
@Data
@TableName(value = "ytx_sys_operate_log")
public class YtxSysOperateLog {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 模块名称
     */
    @TableField(value = "module_name")
    private String moduleName;

    /**
     * 操作类型add、del、update、login、logout
     */
    @TableField(value = "operate_type")
    private String operateType;

    /**
     * 操作详情
     */
    @TableField(value = "operate_desc")
    private String operateDesc;

    /**
     * 操作人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 操作时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}