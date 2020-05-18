package com.yuntongxun.ytx.pojo.dto.req.im.msg;

import lombok.Data;

/**
 * im topic对象
 * @author huangyq
 */
@Data
public class ImMqText {

    /**
     * 此次会话用户帐号
     */
    private String userId;

    /**
     * 消息类型
     * text-纯文本
     * richText-富文本
     * news-图文
     * vedio-视频
     * voice-语音
     * image-图片
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 自定义参数字段
     */
    private String customData;
}
