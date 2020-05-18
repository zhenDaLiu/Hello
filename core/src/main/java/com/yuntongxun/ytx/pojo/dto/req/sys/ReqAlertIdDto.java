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
public class ReqAlertIdDto implements Serializable {

    @NotNull(message = "alertId不能为空")
    @ApiModelProperty(value = "预警ID", required = true)
    private String alertId;

}
