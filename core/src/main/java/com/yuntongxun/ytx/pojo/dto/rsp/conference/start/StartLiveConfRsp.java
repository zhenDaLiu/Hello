package com.yuntongxun.ytx.pojo.dto.rsp.conference.start;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetLiveConfPlayUrlRspLiveData;
import lombok.Data;

import java.util.List;

/**
 * 开始会议旁录直播返回对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class StartLiveConfRsp extends BaseConfRsp {
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
