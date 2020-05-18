package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author cc
 */
@Data
@ApiModel
public class ReqRoleDto implements Serializable {

    @NotBlank(message = "roleName不能为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

}
