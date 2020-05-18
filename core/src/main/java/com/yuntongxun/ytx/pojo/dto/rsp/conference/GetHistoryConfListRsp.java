package com.yuntongxun.ytx.pojo.dto.rsp.conference;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetHistoryConfInfo;
import lombok.Data;

import java.util.List;

/**
 * 会议历史记录返回对象
 * @author sintang
 * @date 2019-05-08
 **/
@Data
public class GetHistoryConfListRsp extends BaseConfRsp {
    /**
     * 会议信息列表
     */
    private List<GetHistoryConfInfo> confs;
}
