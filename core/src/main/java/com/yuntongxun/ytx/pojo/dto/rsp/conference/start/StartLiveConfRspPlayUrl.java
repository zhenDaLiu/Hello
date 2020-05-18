package com.yuntongxun.ytx.pojo.dto.rsp.conference.start;

import lombok.Data;

/**
 * 开始会议旁录直播 拉流地址
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class StartLiveConfRspPlayUrl {
    /**
     * rtmp 流
     */
    private StartLiveConfPlayerUrlSource rtmp;
    /**
     * hls 流
     */
    private StartLiveConfPlayerUrlSource hls;
    /**
     * flv 流
     */
    private StartLiveConfPlayerUrlSource flv;
}
