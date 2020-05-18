package com.yuntongxun.ytx.pojo.dto.req;

/**
 * rest基础请求
 * @author sintang
 * @date 2019-12-02
 */
public abstract class BaseRestReq<T> {
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
