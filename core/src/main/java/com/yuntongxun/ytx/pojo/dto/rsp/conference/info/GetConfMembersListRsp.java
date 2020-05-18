package com.yuntongxun.ytx.pojo.dto.rsp.conference.info;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import lombok.Data;

import java.util.List;

/**
 * 获取会议成员列表返回对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class GetConfMembersListRsp extends BaseConfRsp {
    /**
     * 成员列表
     */
    private List<GetHistoryConfMemberInfo> members;
}
