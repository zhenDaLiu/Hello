package com.yuntongxun.ytx.fast.config;

import com.yuntongxun.ytx.constants.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * RestServer配置
 * @author sintang
 * @date 2020-02-19
 */
@Component
public class RestServerConfig {

//    @Value("${rest.ip}")
//    private String ip;
//
//    @Value("${rest.port}")
//    private String port;
//
//    @Value("${rest.version}")
//    private String version;
//
//    @Value("${rest.appId}")
//    private String appId;
//
//    @Value("${rest.appToken}")
//    private String appToken;
//
//    @Value("${rest.accountSid}")
//    private String accountSid;
//
//    @Bean
//    public GlobalConfig globalConfig(){
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setHttpsRequest(Boolean.TRUE);
//        globalConfig.setRestIp(ip);
//        globalConfig.setPort(Integer.valueOf(port));
//        globalConfig.setVersion(version);
//        globalConfig.setAppId(appId);
//        globalConfig.setAppToken(appToken);
//        globalConfig.setAccountSid(accountSid);
//        return globalConfig;
//    }
}
