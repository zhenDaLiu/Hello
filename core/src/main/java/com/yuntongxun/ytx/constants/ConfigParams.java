package com.yuntongxun.ytx.constants;

import cn.hutool.json.JSONObject;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 参数配置类，把ytx_service_config_info中的数据加载到map中
 * @author tangxy
 * @date 2019-03-22
 **/
public class ConfigParams {
    /**
     * 参数Map
     */
    public static Map<String, JSONObject> paramsMap = Maps.newHashMap();

}
