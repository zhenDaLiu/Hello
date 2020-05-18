package yuntongxun.ytx.fast.init;

import cn.hutool.json.JSONObject;
import com.yuntongxun.ytx.constants.ConfigParams;
import com.yuntongxun.ytx.mapper.config.YtxServiceConfigInfoMapper;
import com.yuntongxun.ytx.pojo.po.config.YtxServiceConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务参数初始化
 * @author tangxy
 * @date 2019-03-22
 **/
@Component
@Slf4j
@Order(value = 1)
public class ServiceConfigParamInit implements CommandLineRunner {

    @Autowired
    private YtxServiceConfigInfoMapper configInfoMapper;

    @Override
    public void run(String... args) throws Exception {
        initYtxServiceConfig();
    }

    private void initYtxServiceConfig(){
        // 初始化ytx_service_config_info表中的数据
//        log.info("初始化ytx_service_config_info ....");
//        List<YtxServiceConfigInfo> ytxServiceConfigInfos = configInfoMapper.selectList(null);
//        if(ytxServiceConfigInfos != null && ytxServiceConfigInfos.size() > 0){
//            JSONObject jsonObject;
//            for(YtxServiceConfigInfo configInfo : ytxServiceConfigInfos){
//                jsonObject = ConfigParams.paramsMap.get(configInfo.getServiceCode());
//                if(jsonObject == null){
//                    jsonObject = new JSONObject();
//                }
//                jsonObject.put(configInfo.getServiceConfigKey(),configInfo.getServiceConfigValue());
//                ConfigParams.paramsMap.put(configInfo.getServiceCode(),jsonObject);
//            }
//        }
    }
}
