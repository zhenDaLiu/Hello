package com.yuntongxun.ytx.constants;

/**
 * 会议常量类
 * @author sintang
 * @date 2019-05-08
 **/
public interface ConfConstants {
    /**
     * restserver 请求url
     */
    enum RestUrl{
        /**
         * HISTORY_CONF_LIST - 获取历史会议列表
         * HISTORY_CONF_RECORDS - 获取会议录音文件列表
         * CALL_RECORDS - 获取rtc点对点通话录制文件列表
         * CREATE_CONF - 创建会议
         * JOIN_CONF - 加入会议
         * QUIT_CONF - 成员退出会议
         * GET_CONF_MEMBERS - 获取会议成员列表
         * INVITE_JOIN_CONF - 邀请加入会议
         * KICK_OUT_MEMBER - 踢出会议成员
         * CONF_INFO - 会议详情
         * PUBLISH_VOICE - 发布语音
         * PUBLISH_VIDEO - 发布视频
         * START_LIVE_CONF - 开始会议旁路直播
         * STOP_LIVE_CONF - 停止会议旁路直播
         * RECORD_CONTROL - 录像控制
         * RECORD_LIST - 录制文件列表
         * GET_LIVE_CONF_PLAY_URL - 获取会议直播播放地址
         */
        HISTORY_CONF_LIST("/Conference/History/Search"),
        HISTORY_CONF_RECORDS("/Conference/Record/List"),
        CALL_RECORDS("/Conference/Ext/RecordP2pGetFileList"),
        CREATE_CONF("/Conference/Create"),
        JOIN_CONF("/Conference/Member/Join"),
        QUIT_CONF("/Conference/Member/Quit"),
        GET_CONF_MEMBERS("/Conference/Member/List"),
        INVITE_JOIN_CONF("/Conference/Member/Invite"),
        KICK_OUT_MEMBER("/Conference/Member/Kickout"),
        PUBLISH_VOICE("/Conference/MediaControl/StartPublishVoice"),
        PUBLISH_VIDEO("/Conference/MediaControl/StartPublishVideo"),
        CONF_INFO("/Conference/Info"),
        START_LIVE_CONF("/Conference/Live/Start"),
        STOP_LIVE_CONF("/Conference/Live/Stop"),
        RECORD_CONTROL("/Conference/MediaControl/Record"),
        RECORD_LIST("/Conference/Record/List"),
        GET_LIVE_CONF_PLAY_URL("/Conference/Live/GetPlayUrl");

        private String url;

        RestUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    /**
     * 获取会议配置参数
     */
    enum ConfigParam{
        /**
         * 获取会议配置参数
         * live_ip - 直播api ip地址
         * live_port - 直播api端口
         * live_rest_version - 直播api 接口版本
         * fwb_info - 服务兵（老环境海尔直播平台信息）
         */
        userId,conf_info,restIp,port,appId,appToken,accountSid,softVersion,live_ip,live_port,live_rest_version,fwb_info
    }

    /**
     * 会议用户角色
     */
    enum ConfMemberRole{
        /**
         * 创建者 1
         * 主持人 2
         * 普通成员 3
         */
        CREATOR(1),ADMIN(2),CUSTMER(3);
        private Integer value;

        ConfMemberRole(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 声音模式
     */
    enum VoiceMode{
        /**
         * 2无提示音无背景音、3有提示音无背景音
         */
        NO_VOICE(2),HAVE_VOICE(3);
        private Integer value;

        public Integer getValue() {
            return value;
        }

        VoiceMode(Integer value) {
            this.value = value;
        }
    }

    /**
     * 录制文件类型
     */
    enum RecordType{
        /**
            * 支持如下几种操作：
            * 开始录制语音 = 0
            * 停止录制语音 = 1
            * 开始录制摄像头视频 = 10
            * 停止录制摄像头视频 = 11
            * 开始录制所有内容 = 40
            * 发布视频时，则只录制音视频；  开启屏幕共享时，则录制屏幕共享
            * 停止录制所有内容 = 41
         */
        START_VOICE(0),STOP_VOICE(1),START_VIDEO(10),STOP_VIDEO(11),START_ALL(40),STOP_ALL(41);
        private Integer value;

        public Integer getValue() {
            return value;
        }

        RecordType(Integer value) {
            this.value = value;
        }
    }

}
