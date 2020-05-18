package com.yuntongxun.ytx.pojo.dto.req.im.msg;

import com.yuntongxun.ytx.constants.ImConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.msg.PushMsgRsp;
import lombok.Data;

import java.util.List;

/**
 * 发送聊天室消息请求对象
 * @author sintang
 * @date 2019-08-07
 **/
@Data
public class PushMsgReq extends BaseImReq<PushMsgRsp> {

    /**
     * 推送类型 5：推送聊天室消息
     * 必填
     */
    private String pushType;

    /**
     * 发送者账号
     * 必填
     */
    private String sender;

    /**
     * 发送者昵称
     */
    private String senderNickName;

    /**
     * 接收者账号，如果是个人，最大上限100人/次，如果是群组，仅支持1个
     * 如果pushType为5，reciver则为聊天室Id
     */
    private List<String> receiver;

    /**
     * 消息类型，1：文本消息，2：语音消息，3：视频消息，
     * 4：图片消息，5：位置消息，6：文件，26：指令消息(如需使用extOpts字段，则设置该类型)
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 扩展字段
     */
    private String msgDomain;

    /**
     * 文件名，最大长度128字节
     */
    private String msgFileName;

    /**
     * 文件绝对路径，
     * http://ip:port/6001/20150314000000110000000000000010/2015-05-07/23-50/1431013806749650943.amr
     */
    private String msgFileUrl;

    /**
     * extOpts（此参数需要进行base64加密）
     * PushMsgExtOpts base64加密
     */
    private String extOpts;

    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ImConstants.RestUrl.PUSH_MSG.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<PushMsgRsp> getResponseClass() {
        return PushMsgRsp.class;
    }
}
