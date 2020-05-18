package com.yuntongxun.ytx.pojo.po.config;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * ytx_service_config_info 实体类 
 * @author tangxy
 * @date 2019-03-15
 */
@Data
@TableName(value = "ytx_service_config_info")
public class YtxServiceConfigInfo {
    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    /**
     * 服务编码
     */
    @TableField(value = "service_code")
    private String serviceCode;

    /**
     * 服务名称
     */
    @TableField(value = "service_name")
    private String serviceName;

    /**
     * 配置key值
     */
    @TableField(value = "service_config_key")
    private String serviceConfigKey;

    /**
     * 配置value值
     */
    @TableField(value = "service_config_value")
    private String serviceConfigValue;

    /**
     * 可用状态 1：可用 2：不可用
     */
    @TableField(value = "service_status")
    private Boolean serviceStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}