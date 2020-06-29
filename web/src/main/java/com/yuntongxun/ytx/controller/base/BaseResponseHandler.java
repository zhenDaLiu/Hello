package com.yuntongxun.ytx.controller.base;

import com.yuntongxun.ytx.pojo.dto.rsp.ClientBaseBodyRspDto;
import com.yuntongxun.ytx.pojo.dto.rsp.ClientBaseRspDto;

/**
 * 封装处理公共响应报文体数据
 * @author tangxy
 * @date 2019-03-09
 **/
public abstract class BaseResponseHandler{
    /**
     * 客户端返回 成功返回
     * @param data
     * @return
     */
    protected <T> ClientBaseRspDto callBack(T data){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().ok();
        ClientBaseBodyRspDto<T> clientBaseBodyRspDto = ClientBaseBodyRspDto.builder().info(data).build().ok();
        clientBaseRspDto.setData(clientBaseBodyRspDto);
        return clientBaseRspDto;
    }

    /**
     * 客户端返回 成功返回
     * @return
     */
    protected ClientBaseRspDto callBack(){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().ok();
        ClientBaseBodyRspDto bodyRspDto = ClientBaseBodyRspDto.builder().build().ok();
        clientBaseRspDto.setData(bodyRspDto);
        return clientBaseRspDto;
    }

    /**
     * 客户端返回 成功返回
     * @param msg 成功信息
     * @return
     */
    protected ClientBaseRspDto callBack(String msg){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().ok(msg);
        return clientBaseRspDto;
    }

    /**
     * 客户端返回 成功返回
     * @return
     */
    protected ClientBaseRspDto callBackBody(String msg){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().ok();
        ClientBaseBodyRspDto bodyRspDto = ClientBaseBodyRspDto.builder().build().ok(msg);
        clientBaseRspDto.setData(bodyRspDto);
        return clientBaseRspDto;
    }

    /**
     * 客户端返回 失败返回，返回数据、返回状态码、返回失败描述
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    protected <T> ClientBaseRspDto callBackFail(T data,Integer code,String msg){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().fail();
        ClientBaseBodyRspDto<T> clientBaseBodyRspDto = ClientBaseBodyRspDto.builder().info(data).build().fail(code, msg);
        clientBaseRspDto.setData(clientBaseBodyRspDto);
        return clientBaseRspDto;
    }

    /**
     * 客户端返回 失败返回，返回状态码、返回失败描述
     * @param code
     * @param msg
     * @return
     */
    protected  ClientBaseRspDto callBackFail(Integer code,String msg){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().ok();
        ClientBaseBodyRspDto bodyRspDto = ClientBaseBodyRspDto.builder().build().fail(code, msg);
        clientBaseRspDto.setData(bodyRspDto);
        return clientBaseRspDto;
    }
}
