package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * 登录请求对象
 * @author tangxy
 */
@Data
@ApiModel
public class ReqLoginDto implements Serializable {

    @NotBlank(message = "username不能为空")
    @ApiModelProperty(value = "账户", required = true)
    private String username;

    @NotBlank(message = "password不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "登录来源,h5、ocx、android")
    private String loginFrom;

}
