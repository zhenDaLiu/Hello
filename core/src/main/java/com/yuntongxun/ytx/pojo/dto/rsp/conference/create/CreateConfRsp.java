package com.yuntongxun.ytx.pojo.dto.rsp.conference.create;

import com.yuntongxun.ytx.pojo.dto.rsp.conference.BaseConfRsp;
import lombok.Data;

/**
 * 创建会议返回对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class CreateConfRsp extends BaseConfRsp {
    /**
     * 会议ID
     */
    private String confId;
}
