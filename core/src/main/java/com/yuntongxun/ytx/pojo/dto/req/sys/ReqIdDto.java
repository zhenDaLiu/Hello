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
public class ReqIdDto implements Serializable {


    @NotNull(message = "行数据id不能为空")
    @ApiModelProperty(value = "行数据id", required = true)
    private Long id;

}
