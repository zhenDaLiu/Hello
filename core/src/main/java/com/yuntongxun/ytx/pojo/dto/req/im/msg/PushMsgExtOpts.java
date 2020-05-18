package com.yuntongxun.ytx.pojo.dto.req.im.msg;

import lombok.Builder;
import lombok.Data;

/**
 * 发送消息附加对象
 * @author sintang
 * @date 2019-08-07
 **/
@Data
@Builder
public class PushMsgExtOpts {
    /**
     * isSave 离线消息数据库是否保存 1:保存，2：不保存
     */
    private String isSave;

    /**
     * 是否走离线推送，1:离线推送，2：不走离线推送，3：不走离线推送，并返回发送者错误码
     */
    private String isOfflinePush;

    /**
     * 是否提示 1：提示，2：静默通知 （接收消息的本地声音）
     */
    private String isHint;

    /**
     * 消息是否同步多设备，1：同步，2：不同步（说明：如果后台已经开启消息同步此参数设置为2，则这条消息就不会同步，如果后台没有开启消息同步，此参数无效）
     */
    private String isSyncMsg;

    private String apsalert;
}
