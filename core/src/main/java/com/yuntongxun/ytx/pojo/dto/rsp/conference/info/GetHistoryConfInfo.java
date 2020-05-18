package com.yuntongxun.ytx.pojo.dto.rsp.conference.info;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import lombok.Data;

import java.util.List;

/**
 * 会议信息
 * @author sintang
 * @date 2019-05-08
 **/
@Data
public class GetHistoryConfInfo extends BaseConfRsp {
    /**
     * 会议id
     */
    private String confId;

    /**
     * 会议名称
     */
    private String confName;

    /**
     * 会议的类型
     * 0：临时会议，所有成员退出后，自动结束；
     * 1：永久会议；
     * 默认值：0
     */
    private Integer confType;


    /**
     * 会议状态，按位取值，获取相关状态，详情参考会议状态(state)的取值说明
     * 0b00000000//空闲 
     * 0b00000001//进行中。历史记录中：为0，会议未开始就被取消；为1，会议开始过，现已结束
     * 0b00000010//已经锁定（此时禁止 成员加入）
     */
    private String state;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建时间
     */
    private String updateTime;

    /**
     * 白板相关信息
     */
    private String wbInfo;

    /**
     * 会议真实的开始时间
     */
    private String startTime;

    /**
     * 会议真实的结束时间
     */
    private String endTime;

    /**
     * 会议预约的开始时间
     */
    private String reserveStartTime;

    /**
     * 会议直播的开始时间
     */
    private String liveStartTime;

    /**
     * 会议的成员总数
     */
    private Integer memberCount;

    /**
     * 启用了会中IM功能时，会返回相关群组ID
     */
    private String chatGroupId;

    /**
     * 指明查询的会议类型
     * 0:当前会议，即未结束的会议（包含预约和正在进行中的会议）
     * 1:历史会议，即已结束的会议（包含已经结束和已经取消的会议）
     */
    private Integer historyConf;

    /**
     * 会议摘要个数
     */
    private Integer confAbstractCount;

    /**
     * 会议文件库个数
     */
    private Integer confFileCount;

    /**
     * 成员的ID列表
     */
    private List<GetHistoryConfMemberInfo> members;
}
