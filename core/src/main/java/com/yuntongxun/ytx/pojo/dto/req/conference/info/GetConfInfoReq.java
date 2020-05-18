package com.yuntongxun.ytx.pojo.dto.req.conference.info;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetHistoryConfInfo;
import lombok.Data;

/**
 * 获取会议信息请求对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class GetConfInfoReq extends BaseConfReq<GetHistoryConfInfo> {

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 指明查询的会议类型
     * 0:当前会议，仅查询未结束的会议（包含预约和正在进行中的会议）
     * 1:历史会议，仅查询已结束的会议（包含已经结束和已经取消的会议）
     * 2:同时查询 未结束会议 和 已结束会议
     * 默认0，仅查询未结束的会议
     */
    private Integer historyConf;

    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.CONF_INFO.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<GetHistoryConfInfo> getResponseClass() {
        return GetHistoryConfInfo.class;
    }
}
