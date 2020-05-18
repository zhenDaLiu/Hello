package com.yuntongxun.ytx.pojo.dto.rsp.conference;

import lombok.Data;

import java.util.List;

/**
 * 获取rtc点对点录音录像 录制文件返回对象
 * @author sintang
 * @date 2019-07-08
 */
@Data
public class GetHistoryCallRecordsRsp extends BaseConfRsp {

    List<GetHistoryCallRecordFile> recordData;
}
