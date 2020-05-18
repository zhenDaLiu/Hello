package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ReqUserIdDto implements Serializable {

    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "账户ID", required = true)
    private Long userId;

}
