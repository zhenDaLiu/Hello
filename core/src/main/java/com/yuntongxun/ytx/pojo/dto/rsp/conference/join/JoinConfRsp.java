package com.yuntongxun.ytx.pojo.dto.rsp.conference.join;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.info.GetHistoryConfInfo;
import lombok.Data;

/**
 * 加入会议返回对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class JoinConfRsp extends BaseConfRsp {

    /**
     * 成员入会时状态(是否打开语音或视频)，
     * 取值同 获取会议成员信息 接口返回的 state 值
     * 客户端按照此值，更改成员入会时状态
     */
    private Integer joinState;

    /**
     * 会议信息
     */
    private GetHistoryConfInfo conf;
}
