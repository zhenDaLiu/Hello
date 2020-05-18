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
public class ReqStatusDto implements Serializable {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "行数据id", required = true)
    private Long id;

    @NotNull(message = "status不能为空")
    @ApiModelProperty(value = "行数据状态 1-有效 2-无效", required = true)
    private Integer status;

}
