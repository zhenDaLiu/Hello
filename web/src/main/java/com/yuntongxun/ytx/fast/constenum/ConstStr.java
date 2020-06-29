package com.yuntongxun.ytx.fast.constenum;

/**
 * 系统相关常量类
 */
public interface ConstStr {

    /**
     * token值
     */
    String ACCESS_TOKEN = "access_token";

    /**
     * 登陆类型 admin-后台管理网站 client-客户端接口
     */
    String LOGIN_TYPE = "login_type";

    /**
     * token值,参数无下划线
     */
    String ACCESS_TOKEN_NO_UNDERLINE = "accessToken";

    /**
     * 登陆类型,参数无下划线 admin-后台管理网站 client-客户端接口
     */
    String LOGIN_TYPE_NO_UNDERLINE = "loginType";


    /**
     * 客户端认证用户状态
     */
    enum ClientAuthUserStatus{
        /**
         * 0：禁用； 1：正常 ；2：未验证
         */
        NO(0),YES(1),PENDING(2);
        private Integer value;

        ClientAuthUserStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * realm登录类型
     */
    enum RealmLoginType{
        /**
         * 后台管理网站 - admin
         * 客户端 - client
         */
        admin,client
    }
}
