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
public class ReqSystemIdDto implements Serializable {

    @NotNull(message = "systemId不能为空")
    @ApiModelProperty(value = "产品ID", required = true)
    private Integer systemId;

}
