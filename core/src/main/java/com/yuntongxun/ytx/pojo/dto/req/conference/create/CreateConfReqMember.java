package com.yuntongxun.ytx.pojo.dto.req.conference.create;

import lombok.Data;

/**
 * 创建会议，参会人员对象
 * @author sintang
 * @date 2019-07-19
 **/
@Data
public class CreateConfReqMember {
    /**
     * 成员ID
     */
    private String memberId;

    /**
     * 成员ID的类型，1: 电话号码，2: 应用账号
     */
    private Integer idType;

    /**
     * 关联电话，idType为应用账号时，才能有此字段。此时用户使用关联的电话参与会议
     */
    private String phoneNumber;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 角色ID
     * 创建者-1
     * 主持人-2
     * 普通成员-3
     */
    private Integer roleId;
}
