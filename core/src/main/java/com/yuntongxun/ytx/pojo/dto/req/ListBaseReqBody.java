package com.yuntongxun.ytx.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 列表请求对象
 * @author wuzw
 * @Date 2019-03-12
 */

@Data
@ApiModel
public class ListBaseReqBody {
	
	private static final long serialVersionUID = 20190312000L;
	
	@ApiModelProperty(value="当前页数，默认【1】为第一页",required=false)
	private Integer page = 1;
	
	@ApiModelProperty(value="页大小，默认【10】",required=false)
	private Integer pageSize = 10 ;
	
}
