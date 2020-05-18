package com.yuntongxun.ytx.pojo.dto.rsp.im.createroom;

import com.yuntongxun.ytx.pojo.dto.rsp.im.BaseImRsp;
import lombok.Data;

/**
 * 创建聊天室返回对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class CreateChatRoomRsp extends BaseImRsp {
    /**
     * 房间ID(32位),生成规则yyyyMMdd+15位随机数+HHmmssSSS
     */
    private String roomId;
}
