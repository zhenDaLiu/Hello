package com.yuntongxun.ytx.pojo.dto.rsp;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础返回对象
 * @author sintang
 * @date 2019-03-09
 **/
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRspDto<HEAD,BODY> implements Serializable {

    @ApiModelProperty(value = "报文头",required = true)
    private HEAD meta;
    @ApiModelProperty(value = "报文体",required = true)
    private BODY data;

}
