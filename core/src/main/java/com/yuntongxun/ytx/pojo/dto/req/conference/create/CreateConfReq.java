package com.yuntongxun.ytx.pojo.dto.req.conference.create;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.create.CreateConfRsp;
import lombok.Data;

import java.util.List;

/**
 * 创建会议
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class CreateConfReq extends BaseConfReq<CreateConfRsp> {

    /**
     * 会议名称，最大256字符
     */
    private String confName;

    /**
     * 会议类型。0：临时会议，所有成员退出后，自动结束；1：永久会议；默认值：0
     */
    private Integer confType;

    /**
     * 普通成员密码，最大32字符
     */
    private String password;

    /**
     * 会议最大成员数。最大不能超过应用中设置的最大数值
     * 默认值：5
     */
    private Integer maxMember;

    /**
     * 2无提示音无背景音、3有提示音无背景音
     * 默认值：3
     */
    private Integer voiceMode;

    /**
     * 主持人离开会议后，会议是否自动结束
     * 0:否，1：是
     * 默认值：1
     */
    private Integer autoClose;

    /**
     * 0：电话会议，1：多流视频，2：混屏视频，3：多流混屏的混合模式，4：实时对讲；
     * 默认值：1
     */
    private Integer mediaType;

    /**
     * 参会成员列表，会议开始时，会自动邀请以下成员中不在线的用户
     */
    private List<CreateConfReqMember> members;

    /**
     * 会议议题，最大1024字节
     */
    private String confTopic;

    /**
     * 应用自定义的字符串，允许的最大长度为1024字节，保存在云平台上，进行透传
     */
    private String appData;

    /**
     * 成员入会时状态(是否打开语音或视频)，
     * 取值同 获取会议成员信息 接口返回的 state 值
     * 默认打开语音和视频 UserStateAllowSpeak / UserStateCameraSharing 
     */
    private String joinState;

    /**
     * 是否启用会中IM
     * chatInConf=1 表示 启用会中IM功能
     * chatInConf=0 表示 不启用会中IM功能
     * 默认值0
     */
    private Integer chatInConf;

    /**
     * 是否自动录制会议
     * KAutoRecordDisable = 0 // 不录制
     * KAutoRecordMembersAll = 1 // 录制所有成员
     * 默认值0
     */
    private Integer autoRecord;

    /**
     * 自动录制类型
     * KStartRecordVoice = 0
     * KStopRecordVoice = 1
     * KVoiceRecordTotal = 2
     * KStartRecordCamera = 10
     * KStopRecordCamera = 11
     * KCameraRecordTotal = 12
     * KStartRecordActionAll = 40 // 开始录制所有内容， 发布视频时，录制音视频 开启屏幕共享时，录制屏幕共享
     * KStopRecordActionAll = 41 // 停止录制所有内容
     */
    private Integer recordAction;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.CREATE_CONF.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<CreateConfRsp> getResponseClass() {
        return CreateConfRsp.class;
    }
}
