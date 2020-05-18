package com.yuntongxun.ytx.pojo.dto.rsp.conference.start;

import lombok.Data;

/**
 * 开始会议直播后拉流地址源对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class StartLiveConfPlayerUrlSource {
    /**
     * url地址
     */
    private String source;
}
