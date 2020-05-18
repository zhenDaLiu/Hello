package com.yuntongxun.ytx.pojo.dto.rsp;

import lombok.Data;

/**
 * 基础相应对象
 * @author: sintang
 * @date: 2019-12-23
 */
@Data
public abstract class BaseRestResponse {
    /**
     * 000000 - 表示成功
     */
    private String statusCode;

    /**
     * 错误信息
     */
    private String statusMsg;
}
