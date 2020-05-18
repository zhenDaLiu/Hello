package com.yuntongxun.ytx.constants;

/**
 * im 相关常量类配置
 * @author sintang
 * @date 2019-07-19
 **/
public interface ImConstants {
    /**
     * 请求接口url
     */
    enum RestUrl{
        /**
         * CREATE_CHAT_ROOM -  创建聊天室
         * TOGGLE_STATE -  切换聊天室状态
         * PUSH_MSG -  服务器推送聊天室消息
         * CREATE_GROUP - 创建群组
         * INVITE_JOIN_GROUP 群组管理员邀请用户加入群组
         * QUERY_GROUP_MEMBER - 查询群组成员
         * DELETE_GROUP_MEMBER - 删除群组成员
         */
        CREATE_CHAT_ROOM("/IM/createChatRoom"),
        TOGGLE_STATE("/IM/ToggleState"),
        PUSH_MSG("/IM/PushMsg"),
        CREATE_GROUP("/IM/Group/CreateGroup"),
        INVITE_JOIN_GROUP("/IM/Group/InviteJoinGroup"),
        QUERY_GROUP_MEMBER("/IM/Member/QueryMember"),
        DELETE_GROUP_MEMBER("/IM/Group/DeleteGroupMember")
        ;

        private String url;

        RestUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
