package com.yuntongxun.ytx.pojo.dto.rsp.conference.info;

import lombok.Data;

/**
 * 获取会议播放地址，会议信息对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class GetLiveConfPlayUrlRspLiveData {

    /**
     * 播放对象
     */
    private GetLiveConfPlayUrlRspLiveDataPlay playData;

    /**
     * 直播会话ID
     */
    private String sessionId;
}
