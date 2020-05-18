package com.yuntongxun.ytx.pojo.dto.rsp.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 管理员集合返回对象
 * @author qijs
 * @date 19/08/14
 */
@ApiModel
@Data
public class AdminsRspDto {
    @ApiModelProperty(value = "主键编码")
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    private String userNiceName;

    @ApiModelProperty(value = "登录账号")
    private String userLogin;

    @ApiModelProperty(value = "所属平台")
    private String userPlatform;

    @ApiModelProperty(value = "所属产业")
    private String userIndustry;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户状态 1：启用 ；2：禁用")
    private Integer userStatus;
}
