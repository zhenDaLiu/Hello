package com.yuntongxun.ytx.pojo.dto.req.conference.join;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetHistoryConfMemberInfo;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.join.InviteJoinConfRsp;
import lombok.Data;

import java.util.List;

/**
 * 邀请加入会议
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class InviteJoinConfReq extends BaseConfReq<InviteJoinConfRsp> {

    /**
     * 执行成功，会给所有成员发送 成员加入通知 或 成员信息更新通知
     * 邀请接口调用成功，即认为这些成员属于会议的成员，获取会议成员列表时，会返回这些成员。
     * 如果要区分 "已经加入会议的成员" 和 "邀请后未加入会议的成员"，可通过成员信息的state字段中 UserStateAppOnline  和 UserStateMediaOnline 标志判断。
     * UserStateAppOnline 为1表明成员已经调用过 Join 接口，可理解为 成员接受会议邀请 ，此状态客户端APP一般不需要使用。
     * UserStateMediaOnline  为1表明成员的媒体通道连接成功，可理解为 真正进入会议 ，相当于在线状态。
     * UserStateAppOnline 置1后，没有其他操作能将其设置为0，
     * 但如果媒体因任何情况断开（App异常退出、手机关机、网络异常、通话挂断等）。UserStateMediaOnline 会自动置0，直到用户再次接通媒体通道。
     */

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 是否立即发起呼叫
     * 对于自定义账号，1表示给用户显示呼叫页面，并设置超时时间1分钟
     * 对于电话号码（或关联电话）账号，1表示立即给cm发呼叫命令
     * 0表示仅在会议中增加成员（一般用户预约会议开始前增加成员）
     * 默认为1
     */
    private Integer callImmediately;

    /**
     * 邀请的会议成员列表
     */
    private List<GetHistoryConfMemberInfo> inviteMembers;

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
        return ConfConstants.RestUrl.INVITE_JOIN_CONF.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<InviteJoinConfRsp> getResponseClass() {
        return InviteJoinConfRsp.class;
    }
}
