package com.yuntongxun.ytx.pojo.dto.req.conference.start;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.start.StartPublishVoiceRsp;
import lombok.Data;

/**
 * 开始发布语音请求对象
 * @author sintang
 * @date 2019-07-23
 **/
@Data
public class StartPublishVoiceReq extends BaseConfReq<StartPublishVoiceRsp> {

    /**
     * 会议编码
     * 必选
     */
    private String confId;

    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.PUBLISH_VOICE.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<StartPublishVoiceRsp> getResponseClass() {
        return StartPublishVoiceRsp.class;
    }
}
