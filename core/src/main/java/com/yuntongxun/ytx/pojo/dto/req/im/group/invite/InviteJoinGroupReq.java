package com.yuntongxun.ytx.pojo.dto.req.im.group.invite;

import com.yuntongxun.ytx.constants.ImConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.group.invite.InviteJoinGroupRsp;
import lombok.Data;

import java.util.List;

/**
 * 加入群组
 * @author sintang
 * @date 2019-12-23
 */
@Data
public class InviteJoinGroupReq extends BaseImReq<InviteJoinGroupRsp> {

    /**
     * 必选
     * appId
     */
    private String appId;

    /**
     * 可选
     * 自定义账号或通讯账号
     */
    private String userName;

    /**
     * 必选
     * 群组ID
     */
    private String groupId;

    /**
     * 必选
     * 被邀请成员，默认一次最多邀请50人
     */
    private List<String> members;

    /**
     * 可选
     * 邀请的理由，最长为50个字符
     */
    private String declared;

    /**
     * 可选
     * 是否需要被邀请人确认 0:需要 1：不需要(自动加入群组) 缺省1
     */
    private String confirm;

    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ImConstants.RestUrl.INVITE_JOIN_GROUP.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<InviteJoinGroupRsp> getResponseClass() {
        return InviteJoinGroupRsp.class;
    }
}