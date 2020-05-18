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
public class ReqMenuDto implements Serializable {

    @NotBlank(message = "菜单URL为空")
    @ApiModelProperty(value = "菜单URL", required = true)
    private String menuCode;

    @NotBlank(message = "菜单名称为空")
    @ApiModelProperty(value = "菜单名称", required = true)
    private String menuName;

    @ApiModelProperty(value = "菜单图标", required = true)
    private String menuIcon;

    @ApiModelProperty(value = "父级菜单ID")
    private Long parentId;

    @NotNull(message = "排序号为空")
    @ApiModelProperty(value = "排序号", required = true)
    private Integer sortNum;

}
