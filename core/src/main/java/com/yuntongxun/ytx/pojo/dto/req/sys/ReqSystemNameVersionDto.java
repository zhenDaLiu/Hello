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
public class ReqSystemNameVersionDto implements Serializable {

    @NotNull(message = "systemName不能为空")
    @ApiModelProperty(value = "产品名称", required = true)
    private String systemName;

    @NotBlank(message = "systemVersion不能为空")
    @ApiModelProperty(value = "产品版本", required = true)
    private String systemVersion;

}
