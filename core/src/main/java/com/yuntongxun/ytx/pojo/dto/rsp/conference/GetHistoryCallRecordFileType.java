package com.yuntongxun.ytx.pojo.dto.rsp.conference;

import lombok.Data;

/**
 * 获取rtc点对点录音录像 返回对象文件信息文件类型
 * @author sintang
 * @date 2019-05-16
 **/
@Data
public class GetHistoryCallRecordFileType {
    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 会议ID
     */
    private String confId;

    /**
     * 文件下载地址
     */
    private String downloadUrl;

    /**
     * 录像文件名
     */
    private String fullname;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 停止时间
     */
    private String stopTime;

    /**
     * 录像时长
     */
    private Long duration;

    /**
     * 任务状态 Recording Completed
     */
    private String status;

    /**
     * 录像文件大小
     */
    private Long size;
}
