package com.yuntongxun.ytx.pojo.dto.rsp.conference.info;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.start.StartLiveConfRspPlayUrl;
import lombok.Data;

import java.util.List;

/**
 * 获取会议播放返回对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class GetLiveConfPlayUrlRsp extends BaseConfRsp {

    /**
     * 新版sdk中不建议使用
     * 拉流url
     */
    private StartLiveConfRspPlayUrl playerUrls;

    /**
     * 新版sdk 直播信息对象
     */
    private List<GetLiveConfPlayUrlRspLiveData> liveData;


}
