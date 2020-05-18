package com.yuntongxun.ytx.pojo.dto.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 列表返回基类
 * @author wuzw
 * @Date 2019-03-12
 */

@Data
@ApiModel
public class ListBaseRspBody<T> extends BaseRspBody {
 
	private static final long serialVersionUID = 20190312001L;
	
	@ApiModelProperty(value="数据总数，默认【0】")
	private Integer total = 0;
	
	@ApiModelProperty(value="数组数据")
	private List<T> result ;
	
}