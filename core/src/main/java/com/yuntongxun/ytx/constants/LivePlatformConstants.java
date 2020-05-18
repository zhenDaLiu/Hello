package com.yuntongxun.ytx.constants;

/**
 * 直播平台常量配置
 * @author sintang
 * @date 2019-07-28
 **/
public interface LivePlatformConstants {

    /**
     * 请求接口url
     */
    enum RestUrl{
        /**
         * GET_CONFIG_CHANNEL -  根据会议号获取直播频道信息
         * GET_LIVE_INFO -  获取频道推流会话记录
         */
        GET_CONFIG_CHANNEL("/livestream/channelInfoByConfId"),
        GET_LIVE_INFO("/livestream/getPublishSession");

        private String url;

        RestUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
