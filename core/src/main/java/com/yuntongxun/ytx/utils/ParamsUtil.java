package com.yuntongxun.ytx.utils;

import org.springframework.util.StringUtils;

/**
 * mybatis 参数处理工具类
 * @author tangxy
 * @date 2019-07-10
 */
public class ParamsUtil {

    private static final String PERCENT = "%";

    public static String joinLike(String str){
        if(StringUtils.isEmpty(str)){
            return "";
        }else{
            String cstr=str.replaceAll("[?]","").replaceAll("[*]","").replaceAll("[#]","");
            return PERCENT.concat(cstr).concat(PERCENT);
        }
    }
}
