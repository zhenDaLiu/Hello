package com.yuntongxun.ytx.constants;

/**
 * rest 常量类
 * @author sintang
 * @date 2019-11-18
 */
public interface RestConstants {
    /**
     * 接口访问超时时间，单位毫秒
     */
    int REST_TIMEOUT = 20000;
    int RESPONSE_200 = 200;
    int RESPONSE_300 = 300;


    /**
     * rest接口成功返回状态编码
     */
    String REST_SUCCESS_CODE = "000000";

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
     * 推送类型
     */
    int PUSHMSG_PUSHTYPE_PERSONAL_ONE = 1;
    int PUSHMSG_PUSHTYPE_GROUP_SECOND = 2;
    int PUSHMSG_PUSHTYPE_ALL_THREE_DEFAULT = 3;


    /**
     * 26 自定义通知类型
     */
    enum CustomType{
        /**
         * 100 - 修改密码通知消息
         * 101 - 账号冻结通知
         * 102 - 账号删除通知
         * 103 - 人员离职
         */
        UPDATE_PWD(100),USER_DISABLE(101),USER_DEL(102),USER_LEAVE(103);
        private Integer value;

        CustomType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
