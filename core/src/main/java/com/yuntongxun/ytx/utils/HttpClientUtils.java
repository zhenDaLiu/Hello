package com.yuntongxun.ytx.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.yuntongxun.ytx.constants.RestConstants;
import com.yuntongxun.ytx.constants.config.GlobalConfig;
import com.yuntongxun.ytx.pojo.dto.req.BaseRestReq;
import com.yuntongxun.ytx.pojo.dto.req.conference.BaseConfReq;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.MyHttpResponse;
import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import com.yuntongxun.ytx.pojo.dto.rsp.im.BaseImRsp;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * http请求工具类
 * @author sintang
 * @date 2019-03-11
 **/
public class HttpClientUtils {
    private static final Log log = LogFactory.get(HttpClientUtils.class);

    private static HttpClientUtils httpClientUtils;

    /**
     * 调用请求信息
     */
    private GlobalConfig config;

    public HttpClientUtils() {
    }

    public HttpClientUtils(String restIp, Integer port, String version, String appId, String appToken, String accountSid){
        this.config = new GlobalConfig(restIp,port,appId,appToken,accountSid,version);
    }

    public HttpClientUtils(GlobalConfig globalConfig){
        this.config = globalConfig;
    }

    public static HttpClientUtils getInstance(GlobalConfig globalConfig){
        if (httpClientUtils == null) {
            synchronized (HttpClientUtils.class){
                httpClientUtils = new HttpClientUtils(globalConfig);
            }
        }
        return httpClientUtils;
    }

    public static HttpClientUtils getInstance(){
        if (httpClientUtils == null) {
            synchronized (HttpClientUtils.class){
                httpClientUtils = new HttpClientUtils();
            }
        }
        return httpClientUtils;
    }


    /**
     * 分隔符
     */
    private static final String SEPARATOR = "/";


    /**
     * 会议 post请求
     * @param restRequest 请求对象
     * @param timeout 超时时间
     */
    public <T extends BaseConfRsp> T confPost(BaseConfReq<T> restRequest, int timeout){
        String restReqStr = JSONUtil.toJsonStr(restRequest);
        log.info("会议请求信息:url:{},参数：{}",restRequest.restUrl(),restReqStr);
        String dateTime = getDateTime();
        StringBuilder url = new StringBuilder();
        if(config.getHttpsRequest()){
            // https请求
            url.append("https://");
        }else{
            url.append("http://");
        }
        if(StrUtil.isEmpty(config.getRestUrl())){
            url.append(config.getRestIp()).append(":").append(config.getPort());
        }else{
            url.append(config.getRestUrl());
        }
        url.append(SEPARATOR).append(config.getVersion())
                .append(SEPARATOR).append("Accounts").append(SEPARATOR).append(config.getAccountSid()).append(restRequest.restUrl())
                .append("?sig=").append(getSig(dateTime));
        String authorization = getAuthorization(dateTime);
        log.info("会议请求信息,请求参数：{},authorization值:{}",url.toString(),authorization);
        HttpRequest request = HttpRequest.post(url.toString())
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .header("Authorization",authorization)
                .body(restReqStr).timeout(timeout);
        HttpResponse response = request.execute();
        log.info("会议返回信息:url:{},返回信息：{}",restRequest.restUrl(),response.body());
        MyHttpResponse rsp = new MyHttpResponse(response.getStatus(),response.body());
        T t = parseRestResponse(restRequest.getResponseClass(), rsp);
        return t;
    }

    /**
     * 会议 post请求
     * @param restRequest 请求对象
     */
    public <T extends BaseConfRsp> T confPost(BaseConfReq<T> restRequest){
        return confPost(restRequest, RestConstants.REST_TIMEOUT);
    }


    /**
     * im post请求
     * @param restRequest 请求对象
     * @param timeout 超时时间
     */
    public <T extends BaseImRsp> T imPost(BaseImReq<T> restRequest, int timeout){
        String restReqStr = JSONUtil.toJsonStr(restRequest);
        log.info("im请求信息:url:{},参数：{}",restRequest.restUrl(),restReqStr);
        String dateTime = getDateTime();
        StringBuilder url = new StringBuilder();
        if(config.getHttpsRequest()){
            // https请求
            url.append("https://");
        }else{
            url.append("http://");
        }
        if(StrUtil.isEmpty(config.getRestUrl())){
            url.append(config.getRestIp()).append(":").append(config.getPort());
        }else{
            url.append(config.getRestUrl());
        }
        url.append(SEPARATOR).append(config.getVersion())
                .append(SEPARATOR).append("Application").append(SEPARATOR).append(config.getAppId()).append(restRequest.restUrl())
                .append("?sig=").append(getSig(dateTime));
        String authorization = getAuthorization(dateTime);
        log.info("im请求信息,请求参数：{},authorization值:{}",url.toString(),authorization);
        HttpRequest request = HttpRequest.post(url.toString())
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .header("Authorization",authorization)
                .body(restReqStr).timeout(timeout);
        HttpResponse response = request.execute();
        log.info("im返回信息:url:{},返回信息：{}",restRequest.restUrl(),response.body());
        MyHttpResponse rsp = new MyHttpResponse(response.getStatus(),response.body());
        T t = parseRestResponse(restRequest.getResponseClass(), rsp);
        return t;
    }

    /**
     * im post请求
     * @param restRequest 请求对象
     */
    public <T extends BaseImRsp> T imPost(BaseImReq<T> restRequest){
        return imPost(restRequest, RestConstants.REST_TIMEOUT);
    }

    /**
     * live post请求
     * @param restRequest 请求对象
     * @param timeout 超时时间
     */
    public <T extends BaseImRsp> T livePost(BaseImReq<T> restRequest, int timeout){
        String restReqStr = JSONUtil.toJsonStr(restRequest);
        log.info("live请求信息:url:{},参数：{}",restRequest.restUrl(),restReqStr);
        StringBuilder url = new StringBuilder();
        if(config.getHttpsRequest()){
            // https请求
            url.append("https://");
        }else{
            url.append("http://");
        }
        if(StrUtil.isEmpty(config.getRestUrl())){
            url.append(config.getRestIp()).append(":").append(config.getPort());
        }else{
            url.append(config.getRestUrl());
        }
        url.append(SEPARATOR).append(config.getVersion())
                .append(SEPARATOR).append("application").append(SEPARATOR).append(config.getAppId()).append(restRequest.restUrl());
        log.info("live请求信息,请求参数：{}",url.toString());
        HttpRequest request = HttpRequest.post(url.toString())
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .body(restReqStr).timeout(timeout);
        HttpResponse response = request.execute();
        log.info("live返回信息:url:{},返回信息：{}",restRequest.restUrl(),response.body());
        MyHttpResponse rsp = new MyHttpResponse(response.getStatus(),response.body());
        T t = parseRestResponse(restRequest.getResponseClass(), rsp);
        return t;
    }

    /**
     * live post请求
     * @param restRequest 请求对象
     */
    public <T extends BaseImRsp> T livePost(BaseImReq<T> restRequest){
        return livePost(restRequest, RestConstants.REST_TIMEOUT);
    }

    /**
     * 解析返回对象
     * @param tClass
     * @param httpResponse
     * @param <T>
     * @return
     */
    private <T> T parseRestResponse(Class<T> tClass, MyHttpResponse httpResponse){
        if (httpResponse.isSuccess()) {
            return JSONUtil.toBean(httpResponse.getRspStr(), tClass);
        }
        return null;
    }


    /**
     * 普通的REST 请求
     * @param restUrl
     * @param restRequest
     * @param <T>
     * @return
     */
    public <T> T post(String restUrl, BaseRestReq<T> restRequest){
        String restReqStr = JSONUtil.toJsonStr(restRequest);
        log.info("Rest请求信息:url:{},参数：{}",restRequest.restUrl(),restReqStr);
        HttpRequest request = HttpRequest.post(restUrl)
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .body(restReqStr).timeout(RestConstants.REST_TIMEOUT);
        HttpResponse response = request.execute();
        log.info("Rest返回信息:url:{},返回信息：{}",restRequest.restUrl(),response.body());
        MyHttpResponse rsp = new MyHttpResponse(response.getStatus(),response.body());
        T t = parseRestResponse(restRequest.getResponseClass(), rsp);
        return t;
    }
    /**
     * 普通的REST 请求
     * @param restUrl
     * @param restRequest
     * @param <T>
     * @param headerMap 自定义header
     * @return
     */
    public <T> T post(String restUrl, BaseRestReq<T> restRequest, Map<String, String> headerMap){
        String restReqStr = JSONUtil.toJsonStr(restRequest);
        log.info("Rest请求信息:url:{},参数：{}",restRequest.restUrl(),restReqStr);
        HttpRequest request = HttpRequest.post(restUrl)
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .body(restReqStr).timeout(RestConstants.REST_TIMEOUT);
        if(!headerMap.isEmpty()){
            Set<Map.Entry<String, String>> entries = headerMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                request.header(next.getKey(),next.getValue());
            }
        }
        HttpResponse response = request.execute();
        log.info("Rest返回信息:url:{},返回信息：{}",restRequest.restUrl(),response.body());
        MyHttpResponse rsp = new MyHttpResponse(response.getStatus(),response.body());
        T t = parseRestResponse(restRequest.getResponseClass(), rsp);
        return t;
    }

    /**
     * Rest请求，返回字符串
     * @param restUrl
     * @param restRequest
     * @return
     */
    public String postStr(String restUrl, BaseRestReq restRequest){
        String restReqStr = JSONUtil.toJsonStr(restRequest);
        log.info("Rest请求信息:url:{},参数：{}",restUrl,restReqStr);
        HttpRequest request = HttpRequest.post(restUrl)
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .body(restReqStr).timeout(RestConstants.REST_TIMEOUT);
        HttpResponse response = request.execute();
        log.info("Rest返回信息:url:{},返回信息：{}",restUrl,response.body());
        return response.body();
    }
    /**
     * Rest请求，返回字符串
     * @param restUrl
     * @param restRequest
     * @param headerMap 自定义header
     * @return
     */
    public String postStr(String restUrl, BaseRestReq restRequest, Map<String, String> headerMap){
        String restReqStr = JSONUtil.toJsonStr(restRequest);
        log.info("Rest请求信息:url:{},参数：{}",restUrl,restReqStr);
        HttpRequest request = HttpRequest.post(restUrl)
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .body(restReqStr).timeout(RestConstants.REST_TIMEOUT);
        if(!headerMap.isEmpty()){
            Set<Map.Entry<String, String>> entries = headerMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                request.header(next.getKey(),next.getValue());
            }
        }
        HttpResponse response = request.execute();
        log.info("Rest返回信息:url:{},返回信息：{}",restUrl,response.body());
        return response.body();
    }

    /**
     * Rest请求，返回字符串
     * @param restUrl
     * @param jsonStr
     * @return
     */
    public String postStr(String restUrl, String jsonStr){
        log.info("Rest请求信息:url:{},参数：{}",restUrl,jsonStr);
        HttpRequest request = HttpRequest.post(restUrl)
                .header("Content-Type","application/json")
                .header("Accept","application/json;charset=utf-8")
                .body(jsonStr).timeout(RestConstants.REST_TIMEOUT);
        HttpResponse response = request.execute();
        log.info("Rest返回信息:url:{},返回信息：{}",restUrl,response.body());
        return response.body();
    }


    /**
     * 获取Authorization
     * 使用Base64编码（应用ID + 冒号 + 时间戳）
     * @param dateTime
     * @return
     */
    private String getAuthorization(String dateTime) {
        StringBuilder auth = new StringBuilder(config.getAppId());
        auth.append(":").append(dateTime);
        return Base64.encode(auth.toString());
    }

    /**
     * 获取sign值
     * @param dateTime
     * @return
     */
    private String getSig(String dateTime){
        StringBuilder sig = new StringBuilder(config.getAppId());
        sig.append(config.getAppToken());
        sig.append(dateTime);
        return DigestUtil.md5Hex(sig.toString());
    }

    /**
     * 获取时间：yyyyMMddHHmmss
     * @return
     */
    private String getDateTime(){
        return DatePattern.PURE_DATETIME_FORMAT.format(new Date());
    }

    public GlobalConfig getConfig() {
        return config;
    }
}
