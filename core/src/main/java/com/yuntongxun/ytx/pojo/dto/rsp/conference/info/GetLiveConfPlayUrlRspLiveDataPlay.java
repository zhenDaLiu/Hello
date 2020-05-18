package com.yuntongxun.ytx.pojo.dto.rsp.conference.info;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.start.StartLiveConfRspPlayUrl;
import lombok.Data;

/**
 * 获取会议播放地址，会议信息对象 播放对象
 * @author sintang
 * @date 2019-07-24
 **/
@Data
public class GetLiveConfPlayUrlRspLiveDataPlay {

    /**
     * 会议直播播放地址
     */
    private StartLiveConfRspPlayUrl playUrls;

//    /**
//     * 备用的播放地址
//     */
//    private StartLiveConfRspPlayUrl backupLinkNode;
}
