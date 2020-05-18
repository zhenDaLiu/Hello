package com.yuntongxun.ytx.pojo.dto.rsp.conference.info;

import lombok.Data;

/**
 * 获取会议成员信息
 * @author sintang
 * @date 2019-05-08
 **/
@Data
public class GetHistoryConfMemberInfo {
    /**
     * 成员ID
     */
    private String memberId;

    /**
     * 成员ID的类型，1: 电话号码，2: 应用账号
     */
    private Integer idType;

    /**
     * 关联电话，idType为应用账号时，才能有此字段。此时用户使用关联的电话参与会议
     */
    private String phoneNumber;

    /**
     * 邀请结果，当用户拒绝邀请时，此字段说明具体原因
     * 完整错误码参考：会议邀请外呼结果通知邀请者
     */
    private String inviteResult;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 角色ID
     * 创建者 1
     * 主持人 2
     * 普通成员 3
     */
    private Integer roleId;

    /**
     * 用户状态
     * 0b00000000//离线
     * 0b00000001//在线
     * 0b00000010//正在说话
     * 0b00000100//正在共享自己的摄像头（视频会议）
     * 0b00001000//正在共享自己的屏幕
     * 0b00010000//在白板中
     * 0b01000000//可讲 非静音状态
     * 0b10000000//可听
     */
    private Integer state;

    /**
     * 入会终端类型 
     * 取值范围 iPhone Android Windows Mac Web
     */
    private String terminalUA;

    /**
     * 视频发布的源。当state第三位为1时，有值：ip:port
     */
    private String videoSource;

    /**
     * 视频发布的源ssrc。当state第三位为1时，有值
     */
    private String videoSsrc;

    /**
     * 视频发布的媒体编码。当state第三位为1时，有值
     */
    private String videoCodec;

    /**
     * 屏幕共享的视频源，当state第四位为1，有值：ip:port
     */
    private String ssSource;

    /**
     * 屏幕共享的视频源ssrc，当state第四位为1时，有值
     */
    private String ssSsrc;

    /**
     * 屏幕共享的媒体编码。当state第四位为1时，有值
     */
    private String ssCodec;

    /**
     * 入会成员版本
     * 7: 表示第三方客户端
     */
    private String version;

    /**
     * 应用的voip账号前缀
     */
    private String voipPrefix;

    /**
     * 如果应用设置了成员的自定义属性，则返回这个字段
     */
    private String appData;

    /**
     * 成员加入的时间，格式：YYYY-MM-DD hh:mm:ss
     */
    private String joinTime;

    /**
     * 离开会议的时间
     */
    private String leaveTime;

    /**
     * 成员信息更新时间，格式：YYYY-MM-DD hh:mm:ss
     */
    private String updateTime;
}
