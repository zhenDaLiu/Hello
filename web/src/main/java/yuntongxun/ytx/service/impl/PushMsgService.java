package yuntongxun.ytx.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.yuntongxun.ytx.constants.RestConstants;
import com.yuntongxun.ytx.constants.config.GlobalConfig;
import com.yuntongxun.ytx.fast.exception.ClientBusinessException;
import com.yuntongxun.ytx.pojo.dto.req.im.msg.PushMsgExtOpts;
import com.yuntongxun.ytx.pojo.dto.req.im.msg.PushMsgReq;
import com.yuntongxun.ytx.pojo.dto.rsp.im.msg.PushMsgRsp;
import com.yuntongxun.ytx.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 消息推送Service
 * @author sintang
 * @date 2020-02-22
 */
@Service
@Slf4j
public class PushMsgService {

    @Autowired
    private GlobalConfig globalConfig;

    /**
     *  消息推送
     * @param uid
     * @param receiver
     * @param content
     */
    @Async("asyncServiceExecutor")
    public void pushMsg(String uid, String receiver, String content, String msgDomain) {
        PushMsgReq pushMsgReq = new PushMsgReq();
        pushMsgReq.setPushType("1");
        pushMsgReq.setSender(uid);
        pushMsgReq.setReceiver(Lists.newArrayList(receiver));
        pushMsgReq.setMsgType("1");
        pushMsgReq.setMsgContent(content);
        pushMsgReq.setMsgDomain(msgDomain);
        PushMsgExtOpts extOpts = PushMsgExtOpts.builder().isHint("1").isOfflinePush("2").isSave("2").isSyncMsg("1").apsalert(content).build();
        pushMsgReq.setExtOpts(Base64.encode(JSONUtil.toJsonStr(extOpts)));

        PushMsgRsp pushMsgRsp = HttpClientUtils.getInstance(globalConfig).imPost(pushMsgReq);
        if(pushMsgRsp != null){
            if(RestConstants.REST_SUCCESS_CODE.equals(pushMsgRsp.getStatusCode())){
                log.info("发送成功，msgId:{}",pushMsgRsp.getMsgId());
            }else {
                log.info("发送失败：{}",pushMsgRsp.getStatusMsg());
                throw new ClientBusinessException("发送失败:"+pushMsgRsp.getStatusMsg());
            }
        }

    }
}
