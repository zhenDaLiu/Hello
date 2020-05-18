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
public class ReqSystemIdVersionDto implements Serializable {

    @NotNull(message = "systemId不能为空")
    @ApiModelProperty(value = "产品ID", required = true)
    private Integer systemId;

    @NotBlank(message = "systemVersion不能为空")
    @ApiModelProperty(value = "产品版本", required = true)
    private String systemVersion;

}
