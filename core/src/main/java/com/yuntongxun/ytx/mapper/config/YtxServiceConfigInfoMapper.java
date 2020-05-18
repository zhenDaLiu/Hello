package com.yuntongxun.ytx.mapper.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntongxun.ytx.pojo.po.config.YtxServiceConfigInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
@Repository
public interface YtxServiceConfigInfoMapper extends BaseMapper<YtxServiceConfigInfo> {
    /**
     * 根据sevice_code和key查询
     * @param serviceCode
     * @param key
     * @return
     */
    List<YtxServiceConfigInfo> selectByServiceCode(@Param(value = "serviceCode") String serviceCode, @Param(value = "key") String key);

    /**
     * 根据serviceCode获取配置信息
     * @param serviceCode
     * @return
     */
    List<YtxServiceConfigInfo> selectByServiceCodeOnly(@Param(value = "serviceCode") String serviceCode);
}




