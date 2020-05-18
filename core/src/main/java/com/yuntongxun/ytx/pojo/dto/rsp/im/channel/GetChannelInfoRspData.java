package com.yuntongxun.ytx.pojo.dto.rsp.im.channel;

import lombok.Data;

/**
 * 频道信息返回数据对象
 * @author sintang
 * @date 2019-07-29
 **/
@Data
public class GetChannelInfoRspData {
    /**
     * 直播的唯一标识直播频道编码
     */
    private String channelId;

    /**
     * 用户ID,应该是sessionid
     */
    private String uid;
}
