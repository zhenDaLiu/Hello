package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单请求dto
 * @author tangxy
 */
@Data
@ApiModel
public class ReqRoleMenuDto implements Serializable {

    @NotNull(message = "roleId不能为空")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    @ApiModelProperty(value = "菜单列表(传菜单id数组,不传则取消全部菜单)", dataType = "Array")
    private List<Long> menuIds = new ArrayList<>();

}
