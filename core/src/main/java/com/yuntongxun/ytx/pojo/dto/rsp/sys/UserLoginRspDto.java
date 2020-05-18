package com.yuntongxun.ytx.pojo.dto.rsp.sys;

import com.yuntongxun.ytx.pojo.dto.req.BaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回appId
 *
 * @author LDZ
 * @date 2019/9/23
 */
@ApiModel
@Data
public class UserLoginRspDto extends BaseReq {
    @ApiModelProperty(value = "登录所需的appID")
    private String appID;

}
