package com.yuntongxun.ytx.pojo.dto.rsp.im.group.create;

import com.yuntongxun.ytx.pojo.dto.rsp.im.BaseImRsp;
import lombok.Data;


/**
 * 创建群组返回
 * @author sintang
 * @date 2019-12-23
 */
@Data
public class CreateGroupRsp extends BaseImRsp {

    /**
     * 群组ID
     */
    private String groupId;
}
