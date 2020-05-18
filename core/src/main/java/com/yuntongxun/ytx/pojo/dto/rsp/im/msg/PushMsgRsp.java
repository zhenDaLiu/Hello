package com.yuntongxun.ytx.pojo.dto.rsp.im.msg;

import com.yuntongxun.ytx.pojo.dto.rsp.im.BaseImRsp;
import lombok.Data;

/**
 * 发送消息返回对象
 * @author sintang
 * @date 2019-08-07
 **/
@Data
public class PushMsgRsp extends BaseImRsp {

    /**
     * 消息id
     */
    private String msgId;
}
