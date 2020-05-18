package com.yuntongxun.ytx.pojo.dto.req.conference.join;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.join.JoinConfRsp;
import lombok.Data;

/**
 * 加入会议请求对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class JoinConfReq extends BaseConfReq<JoinConfRsp> {

    /**
     * 会议编码
     * 必填
     */
    private String confId;

    /**
     * 会议密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 默认为普通成员-3
     * 创建者-1
     * 主持人-2
     * 普通成员-3
     */
    private Integer roleId;

    /**
     * 如果此用户是被邀请加入的，需要带上邀请者的ID
     */
    private String inviter;

    /**
     * 邀请者ID类型：1，电话号码；2，平台账户。默认为2
     */
    private Integer inviterIdType;

    /**
     * 关联电话，idType为应用账号时，才能有此字段。此时用户使用关联的电话参与会议
     */
    private String phoneNumber;

    /**
     * 成员入会时状态(是否打开语音或视频)，
     * 取值同 获取会议成员信息 接口返回的 state 值
     * 默认打开语音和视频
     */
    private Integer joinState;

    /**
     * 入会终端类型 
     * 取值范围 iPhone Android Windows Mac Web
     */
    private String terminalUA;

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
        return ConfConstants.RestUrl.JOIN_CONF.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<JoinConfRsp> getResponseClass() {
        return JoinConfRsp.class;
    }
}
