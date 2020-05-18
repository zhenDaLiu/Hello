package com.yuntongxun.ytx.pojo.dto.req.conference.stop;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.stop.StopLiveConfRsp;
import lombok.Data;

/**
 * 停止会议直播请求对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class StopLiveConfReq extends BaseConfReq<StopLiveConfRsp> {

    /**
     * 会议编码
     */
    private String confId;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.STOP_LIVE_CONF.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<StopLiveConfRsp> getResponseClass() {
        return StopLiveConfRsp.class;
    }
}
