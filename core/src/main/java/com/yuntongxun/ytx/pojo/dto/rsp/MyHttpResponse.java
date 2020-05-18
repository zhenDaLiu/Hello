package com.yuntongxun.ytx.pojo.dto.rsp;

import com.yuntongxun.ytx.constants.RestConstants;
import lombok.Data;

/**
 * httpclient返回对象
 *
 * @author sintang
 * @date 2019-03-14
 **/
@Data
public class MyHttpResponse {
    /**
     * 返回状态码
     */
    private int status;

    /**
     * 返回参数
     */
    private String rspStr;

    public MyHttpResponse(int status, String rspStr) {
        this.status = status;
        this.rspStr = rspStr;
    }

    public boolean isSuccess() {
        if (RestConstants.RESPONSE_200 <= this.status && RestConstants.RESPONSE_300 > this.status) {
            return true;
        }
        return false;
    }
}
