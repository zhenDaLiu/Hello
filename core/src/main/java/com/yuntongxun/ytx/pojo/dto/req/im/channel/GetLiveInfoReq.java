package com.yuntongxun.ytx.pojo.dto.req.im.channel;

import com.yuntongxun.ytx.constants.LivePlatformConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.channel.GetLiveInfoRsp;
import lombok.Data;

/**
 * 根据channel获取到频道直播信息
 * @author sintang
 * @date 2019-07-27
 **/
@Data
public class GetLiveInfoReq extends BaseImReq<GetLiveInfoRsp> {

    /**
     * 直播频道ID
     */
    private String channelId;

    /**
     * 用户编码，sessionID
     */
    private String uid;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return LivePlatformConstants.RestUrl.GET_LIVE_INFO.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<GetLiveInfoRsp> getResponseClass() {
        return GetLiveInfoRsp.class;
    }
}
