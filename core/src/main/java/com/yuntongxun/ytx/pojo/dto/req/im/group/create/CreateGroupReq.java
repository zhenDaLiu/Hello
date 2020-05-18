package com.yuntongxun.ytx.pojo.dto.req.im.group.create;

import com.yuntongxun.ytx.constants.ImConstants;
import com.yuntongxun.ytx.pojo.dto.req.im.BaseImReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.group.create.CreateGroupRsp;
import lombok.Data;

/**
 * 创建群组
 * @author sintang
 * @date 2019-12-23
 */
@Data
public class CreateGroupReq extends BaseImReq<CreateGroupRsp> {

    /**
     * 必选
     * APPID
     */
    private String appId;

    /**
     * 可选
     * 自定义账号或通讯账号
     */
    private String userName;

    /**
     * 必选
     * 群组名字最长为50个字符
     */
    private String name;

    /**
     * 必选
     * 0：临时组(上限100人) 1普通组(上限300人)2：普通组(上限500人) 3：付费普通组 (上限1000人) 4：付费VIP组（上限2000人）
     * 注意：讨论组取值范围0、1、2，如果大于2则默认2
     */
    private Integer type;

    /**
     * 可选
     * 群组公告最长为200个字符
     */
    private String declared;

    /**
     * 申请加入模式 0：默认直接加入1：需要身份验证 2:私有群组缺省0 可选
     */
    private String permission;

    /**
     * 可选
     * 0：讨论组1：群组 缺省1
     */
    private String target;

    /**
     * 用户扩展字段 可选
     */
    private String groupDomain;

    /**
     * 可选
     * 应用层定义，平台只负责存储 例：1：普通群 2：海关官员带业务串号的群组 3：其他
     */
    private String groupLabel;


    /**
     * 请求接口地址
     *
     * @return
     */
    @Override
    public String restUrl() {
        return ImConstants.RestUrl.CREATE_GROUP.getUrl();
    }

    /**
     * 获取返回对象Class
     *
     * @return
     */
    @Override
    public Class<CreateGroupRsp> getResponseClass() {
        return CreateGroupRsp.class;
    }
}
