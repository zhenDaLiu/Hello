package com.yuntongxun.ytx.fast.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步任务
 * @author sintang
 * @date 2019-07-27
 **/
@Component
@Slf4j
public class AsyncTask {

    /**
     * 异步执行方法Test
     * @return
     */
    @Async("asyncServiceExecutor")
    public String test(){
        return "";
    }


}
