package com.yuntongxun.ytx.pojo.dto.rsp.conference;

import lombok.Data;

/**
 * @Author shiyn3
 * @Date 2019/5/10 10:19
 * @Version 1.0
 */
@Data
public class GetHistoryConfRecordFile {
    /**
     * 会议Id
     */
    private String confId;

    /**
     * 录制文件下载地址
     */
    private String fileUrl;

    /**
     * 录制文件类型 1：音频；2：视频； 3：音视频 ；
     */
    private String recordType;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;


    /**
     * 文件大小
     */
    private String fileSize;



}
