package com.yuntongxun.ytx.pojo.dto.rsp;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页返回对象
 * @author tangxy
 * @date 2019-03-19
 **/
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageBaseRspBody<T> {
    @ApiModelProperty(value = "分页对象")
    private PageInfo<T> page;
}
