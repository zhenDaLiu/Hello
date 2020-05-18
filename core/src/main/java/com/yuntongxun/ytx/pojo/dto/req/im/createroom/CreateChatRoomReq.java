package com.yuntongxun.ytx.pojo.dto.req.im.createroom;

import com.yuntongxun.ytx.constants.ImConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.createroom.CreateChatRoomRsp;
import lombok.Data;

/**
 * 创建聊天室请求对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class CreateChatRoomReq extends BaseImReq<CreateChatRoomRsp> {

    /**
     * 房间创建者（用户登录账号）
     * 必选
     */
    private String creator;

    /**
     * 房间名称（最大长度64个字符，只支持中文、字母、数字、下划线）
     * 必选
     */
    private String name;

    /**
     * 公告（最大长度320个字符）
     */
    private String declared;

    /**
     * 扩展字段（最大长度8192个字符）
     */
    private String ext;

    /**
     * 推流地址，与直播相关
     */
    private String pushUrl;

    /**
     * 拉流地址，与直播相关
     */
    private String pullUrl;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ImConstants.RestUrl.CREATE_CHAT_ROOM.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<CreateChatRoomRsp> getResponseClass() {
        return CreateChatRoomRsp.class;
    }
}
