package com.yuntongxun.ytx.pojo.dto.req.conference.start;

import com.yuntongxun.ytx.constants.ConfConstants;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.start.StartLiveConfRsp;
import lombok.Data;

/**
 * 开始会议旁录直播请求对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class StartLiveConfReq extends BaseConfReq<StartLiveConfRsp> {

    /**
     * userId后面需要跟上@1 @2 @20数值
     * @1 表示安卓端
     * @2 表示ios端
     * @20 表示PC端 、ocx也用这个
     * @21 表示
     *
     */

    /**
     * 会议ID
     * 必选
     */
    private String confId;

    /**
     * 必选
     * 分辨率与码率有固定值，根据不同索引，设置这些固定值
     * 宽,高,帧率
     * 0:320,180,15
     * 1:480,270,15"
     * 2:640,360,15
     * 3:960,540,15
     * 4:1280,720,15
     */
    private String resolutionIdx;

    /**
     * 必选
     * 布局 -1 自动
     * 0 第一个大屏，其他小屏
     */
    private String layoutIdx;


    /**
     * 是否录像
     * yes 录像
     * no 不录像
     * 默认不录像
     */
    private String record;

    /**
     * 混屏切换模式:
     * auto 自动，直播画面将所有成员混屏显示
     * single 手动，直播画面由 SwitchMember 接口指定显示的成员
     * 默认 single
     */
    private String model;

    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ConfConstants.RestUrl.START_LIVE_CONF.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<StartLiveConfRsp> getResponseClass() {
        return StartLiveConfRsp.class;
    }
}
