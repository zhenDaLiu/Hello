package com.yuntongxun.ytx.pojo.dto.rsp.im.channel;

import com.yuntongxun.ytx.pojo.dto.rsp.im.BaseImRsp;
import lombok.Data;

import java.util.List;

/**
 * 根据channel获取到频道直播信息
 * @author sintang
 * @date 2019-07-27
 **/
@Data
public class GetLiveInfoRsp extends BaseImRsp {

    /**
     * 返回描述
     */
    private String message;

    /**
     * 返回编码
     */
    private Integer code;


    private List<GetLiveInfoRspData> data;


}
