package com.yuntongxun.ytx.pojo.dto.req.conference;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.GetHistoryConfRecordsRsp;
import lombok.Data;

/**
 * @Author shiyn3
 * @Date 2019/5/10 9:47
 * @Version 1.0
 */
@Data
public class GetHistoryConfRecordsReq extends BaseConfReq<GetHistoryConfRecordsRsp> {

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 指明当前会议类型
     0:当前会议 1:历史会议
     */
    private Integer historyConf;

    /**Integer
     * 按页返回，获取第几页的内容
     * 默认为0，0为第一页
     */
    private Integer pageNo;

    /**
     * 按页返回，每页返回的会议个数，有pageNo字段，此字段才起作用
     * 默认值20，最大值20
     */
    private Integer pageSize;


    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.HISTORY_CONF_RECORDS.getUrl();
    }

    @Override
    public Class<GetHistoryConfRecordsRsp> getResponseClass() {
        return GetHistoryConfRecordsRsp.class;
    }
}
