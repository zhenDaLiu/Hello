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
public class ReqUserRoleDto implements Serializable {

    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "账户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "角色列表(传角色id数组,不传则取消全部角色)", dataType = "Array")
    private List<Long> roleIds = new ArrayList<>();

}
