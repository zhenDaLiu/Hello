package com.yuntongxun.ytx.pojo.dto.req.im.channel;

import com.yuntongxun.ytx.constants.LivePlatformConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.channel.GetChannelInfoRsp;
import lombok.Data;

/**
 * 获取频道信息请求对象
 * @author sintang
 * @date 2019-07-27
 **/
@Data
public class GetChannelInfoReq extends BaseImReq<GetChannelInfoRsp> {

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return LivePlatformConstants.RestUrl.GET_CONFIG_CHANNEL.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<GetChannelInfoRsp> getResponseClass() {
        return GetChannelInfoRsp.class;
    }
}
