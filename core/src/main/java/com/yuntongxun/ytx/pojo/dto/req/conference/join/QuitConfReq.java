package com.yuntongxun.ytx.pojo.dto.req.conference.join;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.join.QuitConfRsp;
import lombok.Data;

/**
 * 成员退出会议请求对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class QuitConfReq extends BaseConfReq<QuitConfRsp> {

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 应用自定义的字符串，允许的最大长度为1024字节
     */
    private String appData;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.QUIT_CONF.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<QuitConfRsp> getResponseClass() {
        return QuitConfRsp.class;
    }
}
