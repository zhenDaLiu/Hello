package com.yuntongxun.ytx.annotation;

import java.lang.annotation.*;

/**
 * Controller 请求日志注解
 * @author tangxy
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerLog {

    /**
     * 请求
     * 不输出日志参数信息 用于过滤敏感信息如 password
     * @return
     */
    String[] requestExcludes() default {};

    /**
     * 响应
     * 不输出日志参数信息 用于过滤敏感信息如 password
     * @return
     */
    String[] responseExcludes() default {};

    /**
     * 默认 输出请求数据
     *
     * @return
     */
    boolean isPrintRequest() default true;

    /**
     * 默认 输出响应数据
     *
     * @return
     */
    boolean isPrintResponse() default false;
}
