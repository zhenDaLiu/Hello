package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cc
 */
@Data
@ApiModel
public class ReqUserPwdDto implements Serializable {

    @NotNull(message = "账户不能为空")
    @ApiModelProperty(value = "账户", required = true)
    private Long userId;

    @NotBlank(message = "旧密码不能为空")
    @ApiModelProperty(value = "旧密码", required = true)
    private String pwdOld;

    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码", required = true)
    private String pwdNew;

}
