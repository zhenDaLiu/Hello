package com.yuntongxun.ytx.pojo.dto.req.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 */
@Data
@ApiModel
public class ReqUserDto implements Serializable {

    @NotBlank(message = "username不能为空")
    @ApiModelProperty(value = "账户", required = true)
    private String username;

    @NotBlank(message = "password不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;

    @ApiModelProperty(value = "角色列表(传角色id数组,不传则取消全部角色)", dataType = "Array")
    private List<Long> roleIds = new ArrayList<>();

}
