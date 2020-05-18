package com.yuntongxun.ytx.pojo.dto.rsp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yuntongxun.ytx.constants.YtxConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础返回head
 * @author sintang
 * @date 2019-03-09
 **/
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRspHead implements Serializable {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "状态值")
    private String msg;
    @ApiModelProperty(value = "成功标识 True/False")
    private Boolean success;
    @ApiModelProperty(value = "时间戳")
    private Long timestamp;


    public BaseRspHead ok(){
        this.code = YtxConstants.RSP_STATUSCODE_SUCCESS;
        this.msg = YtxConstants.RSP_STATUSMSG_SUCCESS;
        this.success = Boolean.TRUE;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public BaseRspHead fail(){
        this.code = YtxConstants.RSP_STATUSCODE_FAIL;
        this.msg = YtxConstants.RSP_STATUSMSG_FAIL;
        this.success = Boolean.FALSE;
        this.timestamp = System.currentTimeMillis();
        return this;
    }
    public BaseRspHead fail(String msg){
        this.code = YtxConstants.RSP_STATUSCODE_FAIL;
        this.msg = msg;
        this.success = Boolean.FALSE;
        this.timestamp = System.currentTimeMillis();
        return this;
    }
    public BaseRspHead fail(Integer code, String msg){
        this.code =code;
        this.msg = msg;
        this.success = Boolean.FALSE;
        this.timestamp = System.currentTimeMillis();
        return this;
    }
}
