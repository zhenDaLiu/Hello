package com.yuntongxun.ytx.pojo.dto.req.im;


import com.yuntongxun.ytx.pojo.dto.rsp.im.BaseImRsp;

/**
 * im 基础请求对象
 * @author sintang
 * @date 2019-07-19
 **/
public abstract class BaseImReq<T extends BaseImRsp>  {

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
