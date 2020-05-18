package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 */
@Data
@ApiModel
public class ReqRolePermissionDto implements Serializable {

    @NotNull(message = "roleId不能为空")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    @ApiModelProperty(value = "权限列表(传权限id数组,不传则取消全部权限)", dataType = "Array")
    private List<Long> permissionIds = new ArrayList<>();

}
