package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author cc
 */
@Data
@ApiModel
public class ReqIdsDto implements Serializable {

    @NotEmpty(message = "ids不能为空")
    @ApiModelProperty(value = "id集合", required = true)
    private List<Long> ids;

}
