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
public class ReqPermissionDto implements Serializable {

    @NotBlank(message = "menuCode不能为空")
    @ApiModelProperty(value = "归属菜单,前端判断并展示菜单使用", required = true)
    private String menuCode;

    @NotBlank(message = "menuName不能为空")
    @ApiModelProperty(value = "菜单的中文释义", required = true)
    private String menuName;

    @NotBlank(message = "permissionCode不能为空")
    @ApiModelProperty(value = "权限的代码/通配符,对应代码中@RequiresPermissions 的value", required = true)
    private String permissionCode;

    @NotBlank(message = "permissionName不能为空")
    @ApiModelProperty(value = "本权限的中文释义", required = true)
    private String permissionName;

    @NotNull(message = "requiredPermission不能为空")
    @ApiModelProperty(value = "是否本菜单必选权限, 1.必选 2非必选 通常是\"列表\"权限是必选", required = true)
    private Integer requiredPermission;

}
