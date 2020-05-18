package com.yuntongxun.ytx.pojo.dto.rsp.im.channel;

import com.yuntongxun.ytx.pojo.dto.rsp.im.BaseImRsp;
import lombok.Data;

/**
 * 获取直播频道信息
 * @author sintang
 * @date 2019-07-27
 **/
@Data
public class GetChannelInfoRsp extends BaseImRsp {

    /**
     * 返回描述
     */
    private String message;

    /**
     * 返回编码 0-成功，404-未根据confid找到对应channel
     */
    private Integer code;

    /**
     * 频道信息返回数据对象
     */
    private GetChannelInfoRspData data;


}
