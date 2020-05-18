package com.yuntongxun.ytx.pojo.dto.req.conference;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import lombok.Data;

/**
 * 会议基础请求对象
 * @author sintang
 * @date 2019-05-08
 **/
@Data
public abstract class BaseConfReq<T extends BaseConfRsp> {
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 用户ID
     */
    private String userId;


    /**
     * 请求接口地址
     * @return
     */
    public abstract String restUrl();

    /**
     * 获取返回对象Class
     * @return
     */
    public abstract Class<T> getResponseClass();
}
