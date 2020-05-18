package com.yuntongxun.ytx.pojo.dto.req.im.state;

import com.yuntongxun.ytx.constants.ImConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.state.ImToggleStateRsp;
import lombok.Data;

/**
 * 更改聊天室状态
 * @author sintang
 * @date 2019-08-16
 **/
@Data
public class ImToggleStateReq extends BaseImReq<ImToggleStateRsp> {

    /**
     * 房间ID
     */
    private String roomId;
    /**
     * 用户账号（房间创建者）
     */
    private String operator;
    /**
     * 状态 1.打开 2.关闭 3.销毁
     */
    private String state;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ImConstants.RestUrl.TOGGLE_STATE.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<ImToggleStateRsp> getResponseClass() {
        return ImToggleStateRsp.class;
    }
}
