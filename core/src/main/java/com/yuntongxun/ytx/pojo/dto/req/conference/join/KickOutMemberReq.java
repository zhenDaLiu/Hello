package com.yuntongxun.ytx.pojo.dto.req.conference.join;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetHistoryConfMemberInfo;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.join.KickOutMemberRsp;
import lombok.Data;

import java.util.List;

/**
 * 提出会议成员请求对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class KickOutMemberReq extends BaseConfReq<KickOutMemberRsp> {

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 踢出的会议成员。包含
     * 成员ID: memberId
     * ID类型: idType
     */
    private List<GetHistoryConfMemberInfo> kickMembers;

    /**
     * 应用自定义的字符串，允许的最大长度为1024字节
     */
    private String appData;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.KICK_OUT_MEMBER.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<KickOutMemberRsp> getResponseClass() {
        return KickOutMemberRsp.class;
    }
}
