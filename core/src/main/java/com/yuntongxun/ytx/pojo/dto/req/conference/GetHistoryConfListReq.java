package com.yuntongxun.ytx.pojo.dto.req.conference;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.GetHistoryConfListRsp;
import lombok.Data;

/**
 * 获取历史会议列表
 * @author sintang
 * @date 2019-05-08
 **/
@Data
public class GetHistoryConfListReq extends BaseConfReq<GetHistoryConfListRsp> {

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 根据下列memberId和memberIdType来进行获取会议列表，
     * 1: 用户创建的会议，2:用户参与的会议，3:用户相关的会议（创建或者参与）
     * 此字段和memberId, memberIdType必须关联使用
     */
    private Integer searchByMember;

    /**
     * 成员ID
     */
    private String memberId;

    /**
     * 成员ID的类型，应用账号还是落地电话号码。
     * 1: 电话号码，2: 应用账号
     */
    private Integer memberIdType;

    /**
     * 会议的类型，永久or临时
     * 0：临时会议，所有成员退出后，自动结束；1：永久会议
     */
    private Integer confType;

    /**
     * 仅查询 未开始的会议 或 进行中的会议
     * 没有这个参数，则返回所有类型的会议
     * 0: 返回未开始的会议
     * 1: 返回进行中的会议
     */
    private Integer stateInProcess;

    /**
     * 仅查询 预约会议 或 非预约会议
     * 没有这个参数，则返回所有类型的会议
     * 是否启用预约功能
     * 0:不启用，1：启用 默认值：0
     */
    private Integer reserveEnable;

    /**
     * 仅查询指定类型的会议
     * 0：电话会议，1：多流视频，2：混屏视频，3：多流混屏的混合模式，4：实时对讲；
     * 没有这个参数，则返回所有类型的会议
     */
    private Integer mediaType;

    /**
     * 会议开始时间起始点
     */
    private String startTimeBegin;

    /**
     * 会议开始时间终点
     */
    private String startTimeEnd;

    /**
     * 按页返回，获取第几页的内容
     * 默认为0，0为第一页
     */
    private Integer pageNo;

    /**
     * 按页返回，每页返回的会议个数，有pageNo字段，此字段才起作用
     * 默认值20，最大值20
     */
    private Integer pageSize;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.HISTORY_CONF_LIST.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<GetHistoryConfListRsp> getResponseClass() {
        return GetHistoryConfListRsp.class;
    }
}
