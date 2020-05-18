package com.yuntongxun.ytx.constants;

/**
 * 常量类
 * @author tangxy
 * @date 2019-03-09
 */
public interface YtxConstants {

    /**
     * 接口根路径 客户端接口
     */
    String REST_ROOT = "/v1";

    /**
     * 接口根路径 后台管理接口
     */
    String ADMIN_ROOT = "/v2";

    /**
     * 项目名称
     */
    String PROJECT_NAME = "容联快速开发平台";


    /**
     * 返回成功状态码
     */
    Integer RSP_STATUSCODE_SUCCESS = 200;

    /**
     * 客户端成功状态码
     */
    Integer CLIENT_STATUSCODE_SUCESS = 0;

    /**
     * 返回成功描述
     */
    String RSP_STATUSMSG_SUCCESS = "success";

    /**
     * 返回失败状态码
     */
    Integer RSP_STATUSCODE_FAIL = 500;
    /**
     * 返回失败状态码
     */
    String RSP_STATUSMSG_FAIL = "failed";

    /**
     * rest加密标识名称
     */
    String SIGN = "sign";
    /**
     * rest加密时间戳名称
     */
    String TIMESTAMP = "timestamp";
    /**
     * rest加密串名称
     */
    String SECRET = "secret";


    /**
     * 可用状态
     */
    Integer ENABLE = 1;
    /**
     * 不可用状态
     */
    Integer DISABLE = 2;

    /**
     * 正常
     */
    Integer NORMAL_FLAG = 1;

    /**
     * 已删除
     */
    Integer DELETE_FLAG = 2;


    /**
     * 临时文件夹路径
     */
    String TEMP_DIR = "./tmp";
    String TEMP = "tmp";


    /**
     * true false 常量设置
     */
    String TRUE = "true";
    String FALSE = "false";

    /**
     * yes no 常量
     */
    Integer YES = 1;
    Integer NO = 0;

    /**
     * redis key 超时时长 单位：秒
     */
    long REDIS_TIME_OUT = 3600L;


    /**
     * 超级管理员角色编码
     */
    String ADMIN_ROLE_CODE = "admin";

    /**
     * 超级管理员
     */
    String ADMIN="超级管理员";

    String MANAGER="普通管理员";
}
