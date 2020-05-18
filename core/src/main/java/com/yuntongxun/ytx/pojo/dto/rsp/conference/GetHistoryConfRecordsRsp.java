package com.yuntongxun.ytx.pojo.dto.rsp.conference;

import lombok.Data;

import java.util.List;

/**
 * @Author shiyn3
 * @Date 2019/5/10 9:48
 * @Version 1.0
 */
@Data
public class GetHistoryConfRecordsRsp  extends BaseConfRsp {

    List<GetHistoryConfRecordFile> confRecordFiles;
}
