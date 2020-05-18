package com.yuntongxun.ytx.pojo.dto.req.im.group.delete;

import com.yuntongxun.ytx.constants.ImConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.group.delete.DeleteGroupMemberRsp;
import lombok.Data;

import java.util.List;

/**
 * 删除群组成员请求对象
 * @author: sintang
 * @date: 2019-12-30
 */
@Data
public class DeleteGroupMemberReq extends BaseImReq<DeleteGroupMemberRsp> {

    /**
     * 自定义账号或通讯账号
     */
    private String userName;

    /**
     * 必选
     * 群组ID
     */
    private String groupId;

    /**
     * 必选
     * 被删除的成员列表
     */
    private List<String> members;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ImConstants.RestUrl.DELETE_GROUP_MEMBER.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<DeleteGroupMemberRsp> getResponseClass() {
        return DeleteGroupMemberRsp.class;
    }
}
