package com.yuntongxun.ytx.pojo.dto.req.sys;

import com.yuntongxun.ytx.pojo.dto.req.BaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回第三方登录所需的appID
 *
 * @author LDZ
 * @date 2019/9/23
 */
@ApiModel
@Data
public class ReqUserAppIdDto extends BaseReq {
    @ApiModelProperty(value = "client_id")
    private String appID;
}
