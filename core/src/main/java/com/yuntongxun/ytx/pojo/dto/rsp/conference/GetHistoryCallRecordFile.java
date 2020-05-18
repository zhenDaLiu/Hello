package com.yuntongxun.ytx.pojo.dto.rsp.conference;

import lombok.Data;

/**
 * 获取rtc点对点录音录像 返回对象文件信息
 * @Author sintang
 * @Date 2019-05-16
 * @Version 1.0
 */
@Data
public class GetHistoryCallRecordFile {

    /**
     * flv 格式
     */
    private GetHistoryCallRecordFileType flv;
    /**
     * h264 格式
     */
    private GetHistoryCallRecordFileType h264;
    /**
     * aac 格式
     */
    private GetHistoryCallRecordFileType aac;
    /**
     * mp4 格式
     */
    private GetHistoryCallRecordFileType mp4;

}
