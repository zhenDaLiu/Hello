package com.yuntongxun.ytx.pojo.dto.rsp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yuntongxun.ytx.constants.YtxConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 客户端基础返回对象
 * @author sintang
 * @date 2019-07-15
 **/
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class ClientBaseRspDto<T> {

    @ApiModelProperty(value = "状态码 200-成功 其他表示失败")
    private Integer ret;

    @ApiModelProperty(value = "状态值")
    private String msg;

    @ApiModelProperty(value = "返回数据对象")
    private ClientBaseBodyRspDto<T> data;

    public ClientBaseRspDto ok(){
        this.ret = YtxConstants.RSP_STATUSCODE_SUCCESS;
        this.msg = YtxConstants.RSP_STATUSMSG_SUCCESS;
        return this;
    }

    public ClientBaseRspDto ok(String msg){
        this.ret = YtxConstants.RSP_STATUSCODE_SUCCESS;
        this.msg = msg;
        return this;
    }

    public ClientBaseRspDto fail(){
        this.ret = YtxConstants.RSP_STATUSCODE_FAIL;
        this.msg = YtxConstants.RSP_STATUSMSG_FAIL;
        return this;
    }

    public ClientBaseRspDto fail(String msg){
        this.ret = YtxConstants.RSP_STATUSCODE_FAIL;
        this.msg = msg;
        return this;
    }

    public ClientBaseRspDto fail(Integer code, String msg){
        this.ret =code;
        this.msg = msg;
        return this;
    }
}
