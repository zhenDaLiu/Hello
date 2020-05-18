package com.yuntongxun.ytx.pojo.dto.rsp.im.channel;

import lombok.Data;

import java.util.List;

/**
 * 获取直播间记录信息对象
 * @author sintang
 * @date 2019-07-27
 **/
@Data
public class GetLiveInfoRspData {
    /**
     * 频道名称
     */
    private String channelName;

    /**
     * 直播时长(秒)
     */
    private Integer alive;

    /**
     * hls回放地址
     */
    private String playUrl;

    /**
     * Mp4回放地址，如果推流时间长，会有多个mp4文件，一般30分钟左右一个mp4文件
     */
    private List<String> mp4PlayUrls;
}
