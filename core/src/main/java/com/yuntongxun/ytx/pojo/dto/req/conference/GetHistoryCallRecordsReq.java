package com.yuntongxun.ytx.pojo.dto.req.conference;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.GetHistoryCallRecordsRsp;
import lombok.Data;

/**
 * 获取RTC点对点录音录像文件请求对象
 * @author sintang
 * @date 2019-05-16
 */
@Data
public class GetHistoryCallRecordsReq extends BaseConfReq<GetHistoryCallRecordsRsp> {

    /**
     * 点对点相关联的会议ID
     */
    private String msConfId;

    /**
     * 点对点相关联的callroute模块ID
     */
    private String crgwId;

    /**
     * 会话ID
     * sessionId = all 时，表示返回当前会议中所有录制会议的文件
     */
    private String sessionId;


    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.CALL_RECORDS.getUrl();
    }

    @Override
    public Class<GetHistoryCallRecordsRsp> getResponseClass() {
        return GetHistoryCallRecordsRsp.class;
    }
}
