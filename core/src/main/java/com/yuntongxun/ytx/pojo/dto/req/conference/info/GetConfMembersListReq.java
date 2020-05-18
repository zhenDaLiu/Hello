package com.yuntongxun.ytx.pojo.dto.req.conference.info;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetConfMembersListRsp;
import lombok.Data;

/**
 * 获取会议成员列表
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class GetConfMembersListReq extends BaseConfReq<GetConfMembersListRsp> {

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
        return ConfConstants.RestUrl.GET_CONF_MEMBERS.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<GetConfMembersListRsp> getResponseClass() {
        return GetConfMembersListRsp.class;
    }
}
