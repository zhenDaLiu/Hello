package com.yuntongxun.ytx.constants.config;

import lombok.Data;

/**
 * 全局配置
 * @author sintang
 * @date 2019/11/18
 */
@Data
public class GlobalConfig {

    /**
     * rest请求地址，待着ip和端口
     */
    private String restUrl;

    /**
     * rest接口ip
     */
    private String restIp;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * appId
     */
    private String appId;

    /**
     * appToken
     */
    private String appToken;

    /**
     * accountSid
     */
    private String accountSid;

    /**
     * rest版本
     */
    private String version;

    /**
     * 是否是https请求
     */
    private Boolean httpsRequest;

    public GlobalConfig() {
    }

    public GlobalConfig(String restIp, Integer port, String appId, String appToken, String accountSid, String version) {
        this.restIp = restIp;
        this.port = port;
        this.appId = appId;
        this.appToken = appToken;
        this.accountSid = accountSid;
        this.version = version;
    }

    public GlobalConfig(Boolean httpsRequest, String restIp, Integer port, String appId, String appToken, String accountSid, String version) {
        this.httpsRequest = httpsRequest;
        this.restIp = restIp;
        this.port = port;
        this.appId = appId;
        this.appToken = appToken;
        this.accountSid = accountSid;
        this.version = version;
    }
}
