package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cc
 */
@Data
@ApiModel
public class ReqUserPageDto implements Serializable {

    @NotNull(message = "pageNum不能为空")
    @ApiModelProperty(value = "请求页码数", required = true)
    private Integer pageNum;

    @NotNull(message = "pageSize不能为空")
    @ApiModelProperty(value = "每页多少条", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "账户名称", required = true)
    private String username;

}
