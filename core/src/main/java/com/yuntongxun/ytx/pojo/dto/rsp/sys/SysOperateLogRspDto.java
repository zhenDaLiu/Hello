package com.yuntongxun.ytx.pojo.dto.rsp.sys;

import com.yuntongxun.ytx.pojo.dto.req.BaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 管理员操作日志 返回对象
 * @author qijs
 * @date 19/08/16
 */
@ApiModel
@Data
public class SysOperateLogRspDto extends BaseReq {
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "操作类型add、del、update、login、logout")
    private String operateType;

    @ApiModelProperty(value = "操作详情")
    private String operateDesc;

    @ApiModelProperty(value = "操作人")
    private String createUser;

    @ApiModelProperty(value = "操作时间")
    private Date createTime;
}
