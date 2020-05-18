package com.yuntongxun.ytx.pojo.dto.req.sys;

import com.yuntongxun.ytx.pojo.po.sys.SysRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登陆用户返回对象
 * @author cc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepUserDto {

    private Long userId;

    private Integer uid;

    private String username;

    private String nickName;

    private List<SysRole> roles;

    private String accessToken;

    /**
     * 头像
     */
    private String avatar;


}
