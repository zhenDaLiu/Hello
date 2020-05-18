package com.yuntongxun.ytx.constants;

/**
 * 枚举类常量
 * @author tangxy
 * @date 2019-03-26
 **/
public interface EnumConstants {


    /**
     * ytx_service_config_info 服务配置表中 ftp相关配置
     */
    enum FtpServerConfig {
        /**
         * ftp - parent key
         * HOSTNAME - 服务ip
         * PORT - 端口号
         * USERNAME - 用户名
         * PASSWORD - 密码
         * BASEPATH - 基础路径
         * NGINX_URL - FTP nginx服务地址
         */
        FTP("ftp"),
        HOSTNAME("ftp_hostname"),
        PORT("ftp_port"),
        USERNAME("ftp_username"),
        PASSWORD("ftp_password"),
        BASEPATH("ftp_basepath"),
        NGINX_URL("nginx_url");
        private String key;

        FtpServerConfig(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    /**
     * 常见问题配置信息 ytx_service_config_info
     */
    enum FaqServerConfig{
        /**
         * faq - 配置参数
         * phone - 客服电话
         */
        faq,phone
    }



    /**
     * 日志相关配置
     */
    enum SystemLog {
        /**
         * 跟踪ID KEY、开始时间、END_TIME
         */
        THREAD_ID, BEGIN_TIME, END_TIME
    }

    /**
     * 用户状态枚举类
     */
    enum UserStatus {
        /**
         * 用户状态 0：禁用； 1：正常 ；2：未验证
         */
        DISABLE(0), ENABLE(1), NO_TEST(2);
        private Integer value;

        UserStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 系统管理员 用户状态
     */
    enum SysUserDeleteStatus{
        /**
         * 是否有效  1有效  2无效
         */
        DISABLE(2), ENABLE(1);
        private Integer value;

        public Integer getValue() {
            return value;
        }

        SysUserDeleteStatus(Integer value) {
            this.value = value;
        }
    }


    /**
     * 操作日志模块
     */
    enum OperateLogModuleName {
        /**
         * user - 用户
         * menu - 菜单
         * role - 角色
         */
        user, menu, role
    }

    /**
     * 操作日志操作类型
     */
    enum OperateLogOperateType {
        /**
         * add - 新增
         * del - 删除
         * update - 更新
         * login - 登录
         * logout - 登出
         */
        add, del, update, login, logout
    }


    /**
     * 管理员用户表ytx_sys_user 用户类型
     */
    enum UserType{
        /**
         * ADMIN - 管理员
         * USER - 普通用户
         */
        ADMIN(1),USER(2);
        private Integer value;

        UserType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 角色类型表 ytx_sys_role
     */
    enum ManagerType{
        /**
         * ADMIN    -超级管理员
         * MANAGER  -普通管理员
         * NORMAL   -普通用户
         */
        ADMIN("超级管理员"),
        MANAGER("普通管理员"),
        NORMAL("普通用户");

        private String type;

        ManagerType(String type) { this.type = type; }

        public String getType() { return type; }
    }


}