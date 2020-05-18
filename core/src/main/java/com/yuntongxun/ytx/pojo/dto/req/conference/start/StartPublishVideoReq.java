package com.yuntongxun.ytx.pojo.dto.req.conference.start;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.start.StartPublishVideoRsp;
import lombok.Data;

/**
 * 开发发布视频
 * @author sintang
 * @date 2019-07-23
 **/
@Data
public class StartPublishVideoReq extends BaseConfReq<StartPublishVideoRsp> {

    /**
     * 会议编码
     * 必选
     */
    private String confId;

    /**
     * 实时对讲 功能
     * 可选
     * 1 表示 控麦，此时仅允许会中有一个人发布语音，如果已经有人发布语音，此接口调用会返回错误
     * 0 表示发布语音，不考虑其他人语音发布状态
     * 默认为0
     */
    private Integer exclusively;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.PUBLISH_VIDEO.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<StartPublishVideoRsp> getResponseClass() {
        return StartPublishVideoRsp.class;
    }
}
