package com.yuntongxun.ytx.pojo.dto.req.conference.info;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetLiveConfPlayUrlRsp;
import lombok.Data;

/**
 * 获取会议直播播放URL
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class GetLiveConfPlayUrlReq  extends BaseConfReq<GetLiveConfPlayUrlRsp> {
    /**
     * 会议ID
     * 必传
     */
    private String confId;

    /**
     * 新版必传
     * 直播会话ID
     * sessionId = all 时，返回当前会议所有直播会议
     */
    private String sessionId;

    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.GET_LIVE_CONF_PLAY_URL.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<GetLiveConfPlayUrlRsp> getResponseClass() {
        return GetLiveConfPlayUrlRsp.class;
    }
}
