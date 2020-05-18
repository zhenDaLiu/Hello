package com.yuntongxun.ytx.pojo.dto.rsp;

import com.yuntongxun.ytx.constants.YtxConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 客户基础返回对象 Body
 * @author sintang
 * @date 2019-07-15
 **/
@Data
@Builder
@ApiModel
public class ClientBaseBodyRspDto<T> {

    @ApiModelProperty(value = "返回编码 0-成功 其他表示失败")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String msg;

    @ApiModelProperty(value = "返回对象")
    private T info;

    public ClientBaseBodyRspDto ok(){
        this.code = YtxConstants.CLIENT_STATUSCODE_SUCESS;
        this.msg = YtxConstants.RSP_STATUSMSG_SUCCESS;
        return this;
    }

    public ClientBaseBodyRspDto ok(String msg){
        this.code = YtxConstants.CLIENT_STATUSCODE_SUCESS;
        this.msg = msg;
        return this;
    }

    public ClientBaseBodyRspDto fail(){
        this.code = YtxConstants.RSP_STATUSCODE_FAIL;
        return this;
    }

    public ClientBaseBodyRspDto fail(String msg){
        this.code = YtxConstants.RSP_STATUSCODE_FAIL;
        this.msg = msg;
        return this;
    }

    public ClientBaseBodyRspDto fail(Integer code,String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }
}
